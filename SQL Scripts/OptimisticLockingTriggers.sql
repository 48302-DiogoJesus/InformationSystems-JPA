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

create or replace trigger GL_Opt
before insert or update on registo_n_proc
for each row
execute function f_opt_lock();

create or replace trigger GL_Opt
before insert or update on registo_invalido
for each row
execute function f_opt_lock();

create or replace trigger GL_Opt
before insert or update on registo_proc
for each row
execute function f_opt_lock();