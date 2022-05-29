package Utils;

import model.InputState;
import model.Parameters.Parameter;

import java.util.*;

public class UIUtils {

    public static class Input {

        public static void getMultipleInputs(ArrayList<Parameter> requiredParameters) {
            for (Parameter param : requiredParameters) {
                while (true) {
                    String parameterName = param.name;
                    Boolean optional = param.optional;

                    System.out.print(parameterName);
                    if (optional)
                        System.out.print(" (optional) ");
                    System.out.println(":");

                    InputState inputState = param.setValue(getString());

                    if (inputState.valid) {
                        break;
                    }
                    System.out.println();
                    System.out.println("Erro > " + inputState.errorMessage + " Tente de novo.");
                    System.out.println();
                }
            }
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
}
