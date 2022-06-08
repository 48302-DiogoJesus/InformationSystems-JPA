package BusinessLogic.Handlers.others;

import DataScopes.DataScope;
import Utils.UI_Utils;
import Utils.Utils.*;
import model.Entities.EstadosGps;
import model.EntityParameters;
import java.util.ArrayList;

public class InsertEstadoGPS {

    public static void run() throws Exception {

        Parameter<String> estado = EntityParameters.ESTADOGPS(false, false);

        Boolean result = UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(estado);}
        });

        if (!result)
            return;

        try (
                DataScope<EstadosGps, String> ds_estado_gps = new DataScope<>(EstadosGps.class);
        ) {
            EstadosGps newEstadoGps = new EstadosGps();
            newEstadoGps.setEstado(estado.value.toString());
            ds_estado_gps.create(newEstadoGps);
            // Vote
            ds_estado_gps.validateWork();
        }
        System.out.println("[DONE] Novo Estado de GPS inserido");
    }
}
