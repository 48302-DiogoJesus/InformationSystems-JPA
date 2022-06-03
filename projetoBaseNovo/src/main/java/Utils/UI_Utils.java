package Utils;

import Utils.Utils.Parameter;
import Utils.Utils.InputState;

import java.util.*;

public class UI_Utils {

    public static Boolean getMultipleInputs(ArrayList<Parameter> requiredParameters) {
        for (Parameter param : requiredParameters) {
            while (true) {
                String parameterName = param.name;
                Boolean optional = param.optional;

                System.out.print(parameterName);
                if (optional)
                    System.out.print(" (optional)");

                if (!param.options.isEmpty()) {
                    System.out.println();
                    System.out.println("| OPTIONS |");
                    param.printValidOptions();
                }

                System.out.println(":");

                String input = getString();

                if (input.equals("exit")) {
                    return false;
                }

                InputState inputState = param.setValue(input);

                if (inputState.valid) {
                    break;
                }
                System.out.println();
                System.out.println("[INVALID VALUE]: " + inputState.errorMessage);
                System.out.println();
            }
        }
        return true;
    }

    public static String getString() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static Integer getInteger() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
}
