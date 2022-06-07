package BusinessLogic.Handlers.others;

import DataScopes.DataScope;
import Utils.UI_Utils;
import Utils.Utils.*;
import model.Entities.EstadosGps;
import model.EntityParameters;

import java.sql.SQLOutput;
import java.util.ArrayList;


public class RemoveEstadoGPS {
    public static void run() throws Exception {

        Parameter<String> estado = EntityParameters.ESTADOGPS(false, true);

        Boolean result = UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(estado);}
        });

        if (!result)
            return;

        try (DataScope<EstadosGps, String> ds_estado_gps = new DataScope<>(EstadosGps.class)) {
            ds_estado_gps.deleteById(estado.value.toString());

            // Vote
            ds_estado_gps.validateWork();
        }
        System.out.println("[DONE] Estado de GPS removido");
    }
}
