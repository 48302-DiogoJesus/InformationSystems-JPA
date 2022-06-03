package BusinessLogic.Handlers.others;

import DataScopes.DataScope;
import model.Entities.EstadosGps;

import java.util.List;
import java.util.Scanner;

public class ListEstadosGPS {
    public static void run() {
        try (
                DataScope<EstadosGps, String> ds_estado_gps = new DataScope(EstadosGps.class);
        ) {
            List<EstadosGps> results = ds_estado_gps.getAll();

            System.out.println("| Estados de GPS |");
            for (EstadosGps estado : results) {
                System.out.println(estado.getEstado());
            }

            // Vote
            ds_estado_gps.validateWork();
        } catch (Exception e) {
            System.out.println("Application Exception: " + e.getMessage());
        }

        System.out.println("Press ENTER to continue...");
        Scanner wait = new Scanner(System.in);
        wait.nextLine();
    }
}
