package BusinessLogic.Handlers.h;

import DataScopes.DataScope;
import Utils.UI_Utils;
import static Utils.Utils.CallProcedure;

import model.Entities.*;
import model.EntityParameters;
import Utils.Utils.Parameter;
import org.postgresql.util.PGobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateVehicle {

    public static void run() throws Exception {
        Parameter matricula = EntityParameters.MATRICULA(false, false);
        Parameter idCliente = EntityParameters.NIF(false, true);
        Parameter idGps = EntityParameters.GPSID(false, true);
        Parameter estadoGps = EntityParameters.ESTADOGPS(false, true);
        Parameter nomeCondutor = EntityParameters.NOMECONDUTOR(false);
        Parameter telefoneCondutor = EntityParameters.TELEFONE(false);
        Parameter numAlarmes = EntityParameters.NUMALARMES(false);
        Parameter longitude = EntityParameters.LONGITUDE(true);
        Parameter latitude = EntityParameters.LATITUDE(true);
        Parameter raio = EntityParameters.RAIO(true);

        Boolean result = UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(matricula); add(idCliente); add(idGps); add(estadoGps);
                add(nomeCondutor); add(telefoneCondutor); add(numAlarmes);
                add(longitude); add(latitude); add(raio);}
        });

        if (!result)
            return;

        try (
                DataScope<Veiculo, String> ds_veiculo = new DataScope<>(Veiculo.class);
                DataScope<ZonaVerde, Integer> ds_zona_verde = new DataScope<>(ZonaVerde.class);
                DataScope<ClienteParticular, String> ds_cliente_particular = new DataScope<>(ClienteParticular.class);
                DataScope<Cliente, String> ds_cliente = new DataScope<>(Cliente.class);
                DataScope<EstadosGps, String> ds_estados_gps = new DataScope<>(EstadosGps.class);
                DataScope<Gps, Integer> ds_gps = new DataScope<>(Gps.class)
        ) {
            Cliente cliente = ds_cliente.getSingle(idCliente.value.toString());
            if (cliente == null) {
                throw new Exception("O cliente com o NIF: " + idCliente + " não existe!");
            }

            boolean isClienteParticular = ds_cliente_particular.getSingle(cliente.getPK()) != null;

            // Se for cliente particular verificar se já tem o número máximo de veículos permitidos
            HashMap<String, Object> queryCarsNumber = new HashMap<>();
            queryCarsNumber.put("id_cliente", "123443211");
            if (isClienteParticular) {
                List veiculos = ds_veiculo.getNative(queryCarsNumber);
                if (veiculos.size() >= 3) {
                    throw new Exception("Este cliente já atingiu o número máximo de veículos permitidos para um cliente particular!");
                }
            }

            EstadosGps eg = ds_estados_gps.getSingle(estadoGps.value.toString());
            Gps gps = ds_gps.getSingle(Integer.parseInt(idGps.value.toString()));
            // Criar veículo
            Veiculo v = new Veiculo();
            v.setMatricula(matricula.value.toString());
            v.setEstado_gps(eg);
            v.setNome_condutor(nomeCondutor.value.toString());
            v.setId_cliente(cliente);
            v.setId_gps(gps);
            v.setNum_alarmes(Integer.parseInt(numAlarmes.value.toString()));
            v.setTelefone_condutor(telefoneCondutor.value.toString());
            ds_veiculo.create(v);

            if (
                    !(
                        longitude.value == null ||
                        latitude.value == null ||
                        raio.value == null
                    )
            ) {
                // Criar zona verde
                ZonaVerde zv = new ZonaVerde();
                zv.setId_veiculo(v);
                zv.setLatitude(latitude.value.toString());
                zv.setLongitude(longitude.value.toString());
                zv.setRaio(Integer.parseInt(raio.value.toString()));
                ds_zona_verde.create(zv);
            }

            // Vote
            ds_veiculo.validateWork();
            ds_zona_verde.validateWork();
            ds_cliente_particular.validateWork();
            ds_cliente.validateWork();
            ds_estados_gps.validateWork();
            ds_gps.validateWork();
        }

        System.out.println("[DONE] Veículo criado com sucesso");
    }
}
