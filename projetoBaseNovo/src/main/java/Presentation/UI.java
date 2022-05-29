package Presentation;

import BusinessLogic.BusinessLogic;
import BusinessLogic.Handler;
import Utils.UIUtils;

import java.util.HashMap;
import java.util.Map;

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
        Commands.put(4, new Command("Remover Cliente Particular", BusinessLogic.removeClienteParticular));
        Commands.put(5, new Command("Total de alarmes para um veículo", BusinessLogic.totalDeAlarmes));
        Commands.put(6, new Command("Exit Program", Exit::run));
    }

    public static void launch() {
        while (true) {
            printOptions();
            Integer option = UIUtils.Input.getInteger();
            Command command = Commands.get(option);
            if (command == null) {
                System.out.println("Invalid Option\n");
                continue;
            }
            // Put try/catch block
            command.handler.run();
        }
    }

    private static void printOptions() {
        System.out.println("Main Menu Options");
        for (Map.Entry<Integer, Command> entry : Commands.entrySet()) {
            System.out.print(entry.getKey());
            System.out.print(": ");
            System.out.println(entry.getValue().details);
        }
        System.out.println("\nChoose an Option from 1 to " + (Commands.size()));
    }

    private static class Exit {
        public static void run() {
            System.exit(0);
        }
    }
}
