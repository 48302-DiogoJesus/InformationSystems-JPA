package BusinessLogic.Handlers.j;

import DataScopes.DataScope;
import Utils.Utils.Parameter;
import Utils.UI_Utils;

import model.Entities.ListAllAlarmes;
import model.Entities.Veiculo;
import model.EntityParameters;

import javax.lang.model.type.NullType;
import java.sql.Timestamp;
import java.util.ArrayList;

public class InserirSobreVistaAlarmes {
    public static void run() {
        Parameter matricula = EntityParameters.MATRICULA(false, true);
        Parameter nomeCondutor = EntityParameters.NOMECONDUTOR(false);
        Parameter latitude = EntityParameters.LATITUDE(true);
        Parameter longitude = EntityParameters.LONGITUDE(true);
        Parameter marcaTemporal = EntityParameters.MARCATEMPORAL(true);

        Boolean result = UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(matricula); add(nomeCondutor); add(latitude); add(longitude);
                add(marcaTemporal);}
        });

        if (!result)
            return;

        Parameter[] args = { matricula, nomeCondutor, latitude, longitude, marcaTemporal };

        try (
            DataScope<ListAllAlarmes, NullType> ds_list_all_alarmes = new DataScope<>(ListAllAlarmes.class);
            DataScope<Veiculo, String> ds_veiculo = new DataScope<>(Veiculo.class)
        ) {
            ListAllAlarmes newItem = new ListAllAlarmes();
            Veiculo v = ds_veiculo.getSingle(matricula.value.toString());
            newItem.setMatricula(v);
            newItem.setNome_condutor(nomeCondutor.value.toString());
            newItem.setLatitude(latitude.value.toString());
            newItem.setLongitude(longitude.value.toString());
            newItem.setMarca_temporal(Timestamp.valueOf(marcaTemporal.value.toString()));

            ds_list_all_alarmes.create(newItem);
        } catch (Exception e) {
            System.out.println("Application Exception: " + e.getMessage());
        }
    }
}
