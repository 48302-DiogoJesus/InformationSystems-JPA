package Utils;

import model.InputState;
import model.InputValidator;

import java.util.*;

public class UIUtils {

    public static class Input {

        public static class Parameter {
            public String name;
            public Boolean optional = false;
            public String value = "";
            public InputValidator validator;
            public Parameter(String name, Boolean optional, InputValidator inputValidator) {
                this.name = name;
                this.optional = optional;
                this.validator = inputValidator;
            }
            public Parameter(String name, InputValidator inputValidator) {
                this.name = name;
                this.validator = inputValidator;
            }
        }

        public static void getMultipleInputs(ArrayList<Parameter> requiredParameters) {
            for (Parameter param : requiredParameters) {
                while (true) {
                    String parameterName = param.name;
                    Boolean optional = param.optional;

                    System.out.print(parameterName);
                    if (optional)
                        System.out.print(" (optional) ");
                    System.out.println(":");

                    param.value = getString();

                    InputState inputState = param.validator.validate(param.value);
                    if (inputState.valid) {
                        break;
                    }
                    System.out.println("Error > " + inputState.errorMessage);
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

        public static Integer getIntegerInBounds(Integer min, Integer max) {
            while (true) {
                Integer input = getInteger();
                if (min <= input && input <= max) {
                    return input;
                } else {
                    System.out.println("Your input must be in range " + min + ".." + max);
                }
            }
        }
    }
}
