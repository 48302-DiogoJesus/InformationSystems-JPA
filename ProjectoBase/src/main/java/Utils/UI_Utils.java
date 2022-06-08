package Utils;

import Utils.Utils.Parameter;
import Utils.Utils.InputState;

import java.lang.reflect.Field;
import java.util.*;

public class UI_Utils {

    private static void repeat(int times, Runnable block) {
        for (int i = 0; i < times; i++) {
            block.run();
        }
    }

    public static void printTable(String tableHeader, List<Object[]> tableData, Class domainClass) {
        List<String> tableColumnNames = Arrays.stream(domainClass.getDeclaredFields()).map(Field::getName).toList();

        String header = "TABLE DATA for '" + tableHeader + "'";
        String pre = "||||||||||||||||||||||||||||";
        String pos = "||||||||||||||||||||||||||||";
        System.out.println(pre + header + pos);

        for (String hname : tableColumnNames) {
            System.out.print(hname.toUpperCase() + " || ");
        }
        System.out.println();
        repeat((pre + header + pos).length(), () -> System.out.print("|"));
        System.out.println();
        for (Object[] row : tableData) {

            for (Object value : row) {
                System.out.print(value + " | ");
            }
            System.out.println();
        }
        repeat((pre + header + pos).length(), () -> System.out.print("|"));
        System.out.println();
    }

    public static Boolean getMultipleInputs(ArrayList<Parameter> requiredParameters) {
        for (Parameter param : requiredParameters) {
            while (true) {
                String parameterName = param.name;
                Boolean optional = param.optional;

                if (optional)
                    parameterName = parameterName.toUpperCase() + " (optional)";
                else
                    parameterName = parameterName.toUpperCase();

                if (!param.options.isEmpty()) {
                    System.out.println("| OPTIONS FOR '" + parameterName  + "' |");
                    param.printValidOptions();
                } else {
                    System.out.println("| " + parameterName + " |");
                }

                System.out.println(":");

                String input = getString();
                System.out.println();

                if (input.equals("exit"))
                    return false;

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

    public static void waitEnter() {
        System.out.println();
        System.out.println("Press ENTER to continue...");
        (new Scanner(System.in)).nextLine();
    }

    public static String getString() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static Integer getInteger() {
        String input = getString();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
