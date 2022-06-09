package BusinessLogic.Handlers.others;

import DataScopes.DataScope;
import model.Entities.EstadosGps;

import java.util.List;

public class ListEstadosGPS {

    public static void run() throws Exception {
        try (
                DataScope<EstadosGps, String> ds_estado_gps = new DataScope<>(EstadosGps.class);
        ) {
            List<EstadosGps> results = ds_estado_gps.getAll();

            System.out.println("| ESTADOS DE GPS |");
            for (EstadosGps estado : results) {
                System.out.println(" > " + estado.getEstado());
            }

            // Vote
            ds_estado_gps.validateWork();
        }
    }
}
