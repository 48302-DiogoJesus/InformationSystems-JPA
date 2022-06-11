/*
 * 2.d)
 * Criar os mecanismos que permitam:
 * - inserir cliente particular
 * - atualizar cliente particular (CC, NIF, nome, morada, cliente que o referenciou); 
 * - remover cliente particular
 * */

-- INSERT CLIENTE PARTICULAR
drop procedure if exists insert_cliente_particular;

create or replace procedure insert_cliente_particular(i_nif char(9), i_cc char(12), i_nome varchar(30), i_morada varchar(40), i_ref_client char(9), i_telefone char(9))
language plpgsql
as
$$
	/*
	declare
		error_msg text;
		error_sqlstate text;
	*/
	begin
	-- São possíveis situações de concorrência em que entre o primeiro insert e o segundo
	-- o referenciador é apagado. Nessas situações o cliente que estamos a inserir fica com
	-- referenciador = null, um estado permitido pela nosso sistema
	
	  insert into cliente(nif, referenciador, nome, morada, telefone) values(i_nif, i_ref_client, i_nome, i_morada, i_telefone);
	  insert into cliente_particular (id_cliente, cc) values(i_nif, i_cc);
	/*
	exception
		when others then
			get stacked diagnostics error_msg = MESSAGE_TEXT,
									error_sqlstate = RETURNED_SQLSTATE;
								
		raise notice 'ERROR HAPPENED';
		raise notice 'Error SQLState: %', error_sqlstate;
		raise notice 'Error Message: %', error_msg;
	*/
	end;
$$;

-- UPDATE CLIENTE PARTICULAR
drop procedure if exists update_cliente_particular;

create or replace procedure update_cliente_particular(i_nif char(9), n_cc char(12), n_nome varchar(30), n_morada varchar(40), n_ref_client char(9))
language plpgsql
as
$$
	begin 

	  update cliente set 
	  referenciador = n_ref_client, 
	  nome = n_nome,
	  morada = n_morada
	  where nif = i_nif;
	 
	  update cliente_particular set 
	  cc = n_cc
	  where id_cliente = i_nif;
	
	end;
$$;


-- REMOVE CLIENTE PARTICULAR
drop procedure if exists remove_cliente_particular;

create or replace procedure remove_cliente_particular(i_nif char(9))
language plpgsql
as
$$
	begin
	-- Com este nível de isolamento garantimos que caso observemos que cliente particular existe 
	-- quando o vamos apagar ele ainda existe dentro da transação
	set transaction isolation level repeatable read;
	
		if
			(select count(*) from cliente_particular where id_cliente = i_nif) = 0 
		then
			raise exception 'Cliente Particular não encontrado!';
			return;
		end if;
		
	    delete from cliente where nif = i_nif;
	end;
$$;

/*
 * 2.e)
 * Criar uma função que devolve o total de alarmes para um determinado ano e veículo
 * passados como parâmetros; caso a matrícula do veículo seja vazia deve devolver as contagens
 * de alarmes para todos os veículos nesse ano
 * */

drop function if exists count_alarmes_returns_table;

create or replace function count_alarmes_returns_table(i_year int, i_veiculo char(8) default null)
returns table (
	veiculo char(8),
	n_of_alarmes bigint
) as
$$
	begin
		return query select 
				v.matricula as veiculo, 
				count(*) as n_of_alarmes
			from
				alarme a, registo r, veiculo v
			where
				a.id_registo = r.id and
				a.id_veiculo = v.matricula and 
				((i_veiculo is null) or i_veiculo = v.matricula) and -- License Plate Match 
				extract (year from r.marca_temporal) = i_year -- Year match
			group by
				v.matricula, (extract (year from r.marca_temporal))::int
			order by
				count(v.matricula) desc;
	end;
$$ language plpgsql;

/*
-- Versão em que é retornado apenas o número de alarmes para cada veículo
drop function count_alarmes;

create or replace function count_alarmes(i_year int, i_veiculo char(8) default null)
returns int as 
$$
	declare
		n_of_alarmes bigint;
	begin
		select
			count(*) into n_of_alarmes
		from
			alarme a, registo r, veiculo v
		where
			a.id_registo = r.id and
			a.id_veiculo = v.matricula and
			((i_veiculo is null) or i_veiculo = v.matricula) and -- License Plate Match 
			extract (year from r.marca_temporal) = i_year; -- Year match
		
		if n_of_alarmes is null then
			return 0;
		else 
			return n_of_alarmes;
		end if;
	end;
$$ language plpgsql;
*/


/*
 * 2.f)
 * Criar um procedimento que será chamado de forma periódica e que processa todos os
 * registos não processados até esse momento. Deve tratar todos os registos existentes na
 * tabela de registos não processados, de forma a copiar os dados para a tabela de registos
 * processados ou de registos inválidos e remover as entradas tratadas. Deve garantir que
 * processa todas as entradas não processadas até esse momento
 * */

drop procedure if exists handle_registos;

create or replace procedure handle_registos()
as
$$
	declare
		curr_registo_n_proc record;
		curr_id_gps int;
		curr_longitude varchar(20);
		curr_latitude varchar(20);
		curr_marca_temporal timestamp;
	begin
		-- Possíveis problemas de concorrência:
			--> não deve ser possível apagar o registo das tabelas 'registos' e 'registos_n_proc'
			--  enquanto ele estiver a ser tratado (dentro do loop)
			
		for curr_registo_n_proc in (select id_registo from registo_n_proc) loop
			-- Gather column values
			select 
				id_gps, longitude, latitude, marca_temporal
				into
				curr_id_gps, curr_longitude, curr_latitude, curr_marca_temporal
			from
				registo
			where registo.id = curr_registo_n_proc.id_registo;
		
			-- Escolher para onde transferir o 'registo'
			if
				curr_longitude is null or
				curr_latitude is null or
				curr_marca_temporal is null or
				(select count(*) from gps where id = curr_id_gps) = 0
			then
				insert into registo_invalido(id_registo) values(curr_registo_n_proc.id_registo);
			else
				insert into registo_proc(id_registo) values(curr_registo_n_proc.id_registo);
			end if;
		
			-- Remover o registo da tabela 'registos_n_proc'
			delete from registo_n_proc where registo_n_proc.id_registo = curr_registo_n_proc.id_registo;
			
		end loop;
	end;
$$ language plpgsql;


/*
 * 2.g)
 * Criar um gatilho que permita analisar o registo processado, aquando da sua criação. e que
 * gere o respetivo alarme caso esteja fora de qualquer uma das suas zonas verdes. Se não
 * existirem zonas verdes ou se o equipamento estiver em pausa de alarmes não gera alarme.
 * Para a realização do gatilho, deverá usar a função zonaVerdeValida que recebe as
 * coordenadas e o raio de uma zona verde e as coordenadas do registo em processamento e
 * retorne true se as coordenadas do registo a ser processado se encontrarem dentro da zona
 * verde e false caso contrário. Para teste implemente uma versão da função zonaVerdeValida
 * que retorna true quando a o arredondamento da coordenada da latitude do registo for par e
 * false quando for impar;
 * */

drop function if exists analise_registo cascade;

/*
* Retorna TRUE caso o arredondamento da latitude seja um número par, FALSE se for ímpar
*/
create or replace function zonaVerdeValida(id_registo int)
returns boolean
language plpgsql
as
$$
    begin
	    if
	    	mod((select latitude::decimal::int from registo where id = id_registo), 2) = 0
	    then 
	        return true;
	    end if;
	   
        return false;
    end;
$$; 

create or replace function analise_registo()
returns trigger
language plpgsql
as
$$
    declare 
        veiculo_id char(8);
    begin
        
        select matricula from veiculo where veiculo.id_gps = (
       		select id_gps from registo where new.id_registo = registo.id
        ) into veiculo_id;
        if
          	-- Existem zonas verdes para o veiculo
	        (select count(id) from zona_verde where zona_verde.id_veiculo = veiculo_id) <> 0 and 
            -- Equipamento não está em PausaDeAlarmes
            (select estado_gps from veiculo where veiculo.matricula = veiculo_id) <> 'PausaDeAlarmes' and 
        	-- Veiculo fora da zona verde
            not zonaVerdeValida(new.id_registo) 
        then 
            insert into alarme(id_registo, id_veiculo) values(new.id_registo, veiculo_id);
        end if;
        
        return new;
    end;
$$;


-- TRIGGER --

drop trigger if exists analise_registo on registo_proc;

create or replace trigger analise_registo 
after insert on registo_proc
for each row
execute function analise_registo();


/*
 * 2.h)
 * Desenvolver um procedimento que crie um veículo com a respectiva informação do
 * equipamento associado, e que o associe a um cliente. Caso sejam passados dados para a
 * criação de uma zona verde, deve criar e associar o veículo a essa zona. Reutilize
 * procedimentos já existentes ou crie novos se necessário; Deve garantir as restrições de
 * negócio respectivas, nomeadamente a limitação do número de veículos
 * */

drop procedure if exists create_veiculo;

create or replace procedure create_veiculo(
	-- Veículo
	matricula char(8), i_id_cliente char(9), id_gps int, estado_gps varchar(20),
	nome_condutor varchar(30), telefone_condutor char(9), num_alarmes int,
	-- Zona Verde (Opcional) caso 
	longitude varchar(20) default null, latitude varchar(20) default null, raio int default null
)
language plpgsql
as
$$
	declare
		n_of_veiculos_cp bigint;
	begin
	
		raise notice '';
	    -- Tipo de cliente: particular máx 3
		if
			(select count(*) from cliente_particular as cp where cp.id_cliente = i_id_cliente) is not null
		then
			-- É cliente particular
			select count(*) into n_of_veiculos_cp 
			from veiculo
			where veiculo.id_cliente = i_id_cliente;
			
			-- Excedeu número de veículos
			if n_of_veiculos_cp >= 3 then
				raise notice 'Cliente já excedeu número de veículos';
				return;
			end if;	
		end if;
		
	    -- Criar veiculo
		insert into veiculo values(matricula, i_id_cliente, id_gps, estado_gps, nome_condutor, telefone_condutor, num_alarmes);
	
	    -- Há Zona verde ?
		if 
			longitude is null or
			latitude is null or
			raio is null
		then
			raise notice 'Zona Verde não foi passada. Veículo criado com sucesso!';
			return;
		end if;
		
	    -- Se sim, criar zona verde
		insert into zona_verde(id_veiculo, longitude, latitude, raio) 
		values(matricula, longitude, latitude, raio);
	
		raise notice 'Zona Verde criada com sucesso! Veículo criado com sucesso!';
	
	end;
$$;

-- Nota: Zona Verde apenas é criada caso os 3 parâmetros sejam passados


/*
 * 2.i)
 * Criar uma vista, que liste todos os alarmes. A vista deve apresentar a matrícula do veículo, o
 * nome do condutor, as coordenadas e a data/hora do alarme;
 * */

drop view if exists list_all_alarmes;

create or replace view list_all_alarmes as (
    select 
        v.matricula, v.nome_condutor, r.latitude, r.longitude,
        r.marca_temporal
    from
        alarme as a inner join veiculo as v on a.id_veiculo = v.matricula
                    inner join registo as r    on a.id_registo = r.id
);


/*
 * 2.j)
 * Adicione suporte de modo que a execução da instrução INSERT sobre a vista da alínea
 * anterior permita adicionar um alarme e o respectivo registo tratado;
 * */
 
-- RULE PROCEDURE --

drop trigger if exists add_to_list_alarme_and_registo on list_all_alarmes;
drop function if exists add_alarme_and_registo;

create or replace function add_alarme_and_registo()
returns trigger
language plpgsql
as
$$
    declare 
   		registo_id int;
    	gps_id int;
    begin

	   	if 
	   	(
	   		select count(*) from veiculo where 
	   		matricula = new.matricula and
	   		nome_condutor = new.nome_condutor
	   	) = 0
   		then	
	   		raise exception 'O veiculo com a matricula % não se encontra na tabela veiculo ou o nome do condutor para o veículo não está correto!', new.matricula;
	   		return null;
	   	end if;
	   
	    select id_gps into gps_id from veiculo v where v.matricula = new.matricula;
	   
        insert into registo (id_gps, longitude, latitude, marca_temporal) 
            values (gps_id, new.longitude, new.latitude, new.marca_temporal) 
            returning id into registo_id;
           
        insert into alarme (id_registo, id_veiculo) values (registo_id, new.matricula);
       
        return null;
    end;
$$;

-- TRIGGER --

create or replace trigger add_to_list_alarme_and_registo
instead of insert on list_all_alarmes
for each row
execute function add_alarme_and_registo();


/*
 * 2.k)
 * Implemente o procedimento que será chamado diariamente e que apaga os registos
 * inválidos existentes com duração superior a 15 dias;
 * */

drop procedure if exists remove_reg_invalidos;

create or replace procedure remove_reg_invalidos()
language plpgsql
as
$$
	declare 
		ri_cursor cursor for (select id_registo from registo_invalido);
		current_ri_id_registo int;
		current_ri_time date;
	begin
		open ri_cursor;
		
		while true loop
			-- Avançar o cursor uma posição
			fetch from ri_cursor into current_ri_id_registo;
			
			-- Fim da tabela (não existem mais linhas)
			if not found then
				return;
			end if;
		
			select marca_temporal into current_ri_time::date
			from registo
			where id = current_ri_id_registo;
			
			if
				(current_date - current_ri_time) <= 15
				and
				(current_date - current_ri_time) is not null
			then
				continue;
			end if;
			
			-- Passaram mais de 15 dias OU 'marca_temporal' = NULL
				
			-- Apagar da tabela 'Registo_Invalido'
			delete from registo_invalido where current of ri_cursor;
			-- Apagar da tabela 'Registo'
			delete from registo where id = current_ri_id_registo;
		
			continue;
		end loop;
	
		close ri_cursor;
		
	end;
$$;


/*
 * 2.l)
 * Crie os mecanismos necessários para que a execução da instrução DELETE sobre a tabela de
 * cliente permita desativar o(s) Cliente(s) sem apagar os seus dados. Assuma que podem ser
 * apagados vários clientes em simultâneo
 * */

-- TRIGGER FUNCTION --

drop function if exists remove_client cascade;

create or replace function remove_client()
returns trigger
language plpgsql
as
$$
	begin
		update cliente set apagado = true where nif = old.nif;
		-- Retornar NULL por forma a evitar que ação do trigger se complete
		return null;
	end;
$$;


-- TRIGGER --

drop trigger if exists remove_client on cliente;

create or replace trigger remove_client 
before delete on cliente
for each row
when (not old.apagado)
	execute function remove_client();


/*
 * 2.m)
 * Crie os mecanismos necessários para que, de forma automática, quando é criado um
 * alarme, o número de alarmes do veículo seja actualizado;
 * */

-- TRIGGER FUNCTION --

drop function if exists increment_alarm_count;

create or replace function increment_alarm_count()
returns trigger
language plpgsql
as
$$
	begin
		
		update veiculo 
		set num_alarmes = num_alarmes + 1 
		where matricula = new.id_veiculo;

		return null;
	end;
$$;


-- TRIGGER --

drop trigger if exists increment_alarm_count on alarme;

create or replace trigger increment_alarm_count 
after insert on alarme
for each row
execute function increment_alarm_count();

/*
 * Optimistic Locking Trigger
 * */
create or replace function f_opt_lock() returns trigger
language plpgsql
as $$
declare
	c int;
begin
	if new.vers is null then
		new.vers = 0;
	elseif new.vers = old.vers then
		new.vers = new.vers + 1;
	end if;
	return new;
end; $$;

create or replace trigger GL_Opt
before insert or update on registo
for each row
execute function f_opt_lock();
