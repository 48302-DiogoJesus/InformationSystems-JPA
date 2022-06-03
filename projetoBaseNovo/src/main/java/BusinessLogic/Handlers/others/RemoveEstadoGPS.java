package BusinessLogic.Handlers.others;

import DataScopes.DataScope;
import Utils.UI_Utils;
import Utils.Utils.*;
import model.Entities.EstadosGps;
import model.EntityParameters;
import java.util.ArrayList;


public class RemoveEstadoGPS {
    public static void run() {

        Parameter<String> estado = EntityParameters.ESTADOGPS();

        UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(estado);}
        });

        try (
                DataScope<EstadosGps, String> ds_estado_gps = new DataScope(EstadosGps.class);
        ) {

            ds_estado_gps.deleteById(estado.value.toString());

            // Vote
            ds_estado_gps.validateWork();

        } catch (Exception e) {
            System.out.println("Application Exception: " + e.getMessage());
        }
    }
}
