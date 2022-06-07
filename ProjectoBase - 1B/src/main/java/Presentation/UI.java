package Presentation;

import BusinessLogic.BusinessLogic;
import BusinessLogic.Handlers.Handler;

import java.util.HashMap;
import java.util.Map;

import static Utils.UI_Utils.getInteger;
import static Utils.UI_Utils.waitEnter;

public class UI {

    private static class Command {
        public String details;
        public Handler handler;
        public Command(String details, Handler handler) {
            this.details = details;
            this.handler = handler;
        }
    }

    static HashMap<Integer, Command> Commands = new HashMap<>();

    static {
        Commands.put(1, new Command("Inserir Cliente Particular", BusinessLogic.insertClienteParticular));
        Commands.put(2, new Command("Remover Cliente Particular", BusinessLogic.removeClienteParticular));
        Commands.put(3, new Command("Atualizar Cliente Particular", BusinessLogic.updateClienteParticular));
        Commands.put(4, new Command("Total de alarmes para um veículo", BusinessLogic.totalDeAlarmes));
        Commands.put(5, new Command("Processamento de registos não processados", BusinessLogic.handleRegistos));
        Commands.put(6, new Command("Inserir Veiculo", BusinessLogic.createVehicle));
        Commands.put(7, new Command("Inserir Estado GPS", BusinessLogic.insertEstadoGPS));
        Commands.put(8, new Command("Remover Estado GPS", BusinessLogic.removeEstadoGPS));
        Commands.put(9, new Command("Listar Estados GPS", BusinessLogic.listEstadoGPS));
        Commands.put(10, new Command("Inserir sobre a vista de Alarmes", BusinessLogic.inserirSobreVistaAlarmes));
        Commands.put(11, new Command("Listar Alarmes", BusinessLogic.listAlarmes));
        Commands.put(12, new Command("Apagar Registos Inválidos", BusinessLogic.deleteRegistosInvalidos));
        Commands.put(13, new Command("Exit Program", Exit::run));
    }

    public static void launch() {
        while (true) {
            printOptions();
            Integer option = getInteger();
            Command command = Commands.get(option);
            System.out.println();

            if (command == null) {
                System.out.println("[INVALID OPTION]\n");
                continue;
            }

            try  {
                System.out.println("type 'exit' to go back to the menu\n");
                command.handler.run();
                waitEnter();

            } catch (Exception e) {
                System.out.println();
                System.out.println("[UNCAUGHT ERROR]: " + e.getMessage());
                e.printStackTrace();
                System.out.println();
            }
        }
    }

    private static void printOptions() {
        System.out.println("\n| -- MAIN MENU OPTIONS -- |");
        for (Map.Entry<Integer, Command> entry : Commands.entrySet()) {
            System.out.print(entry.getKey());
            System.out.print(") ");
            System.out.println(entry.getValue().details);
        }
        System.out.println("\n> Choose an Option from 1 to " + (Commands.size()) + ":");
    }

    private static class Exit {
        public static void run() {
            System.exit(0);
        }
    }
}
