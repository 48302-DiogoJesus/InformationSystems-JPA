package BusinessLogic.Handlers.e;

import Utils.Utils;
import Utils.UIUtils.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Utils.Utils.ReturnType;
import model.Parameters.Parameter;
import model.Parameters.Parameters;

import static Utils.Utils.CallProcedure;

public class TotalDeAlarmes {

    public static class TotalDeAlarmesResult {
        public String matricula;
        public Long n_of_alarmes;
        public TotalDeAlarmesResult(String matricula, Long n_of_alarmes) {
            this.matricula = matricula;
            this.n_of_alarmes = n_of_alarmes;
        }
    }

    public static void run() {
        // Ask year
        Parameter ano = Parameters.ANO();
        // Ask matricula
        Parameter matricula = Parameters.MATRICULA();

        Input.getMultipleInputs(new ArrayList<>() {
            {add(ano); add(matricula);}
        });

        Parameter[] args = { ano, matricula };

        List<Object[]> list = CallProcedure(
                "count_alarmes_returns_table",
                args,
                Utils.ProcedureType.FUNCTION,
                ReturnType.TABLE
        );

        printTable(convertToTable(list), (String) matricula.value, (Integer) ano.value);

        System.out.println("Press ENTER to continue...");
        Scanner wait = new Scanner(System.in);
        wait.nextLine();
    }

    private static ArrayList<TotalDeAlarmesResult> convertToTable(List<Object[]> list) {
        ArrayList<TotalDeAlarmesResult> resultsList = new ArrayList<>(list.size()) {};
        for (Object[] item : list) {
            resultsList.add(new TotalDeAlarmesResult((String) item[0], (Long) item[1]));
        }
        return resultsList;
    }

    private static void printTable(ArrayList<TotalDeAlarmesResult> table, String matricula, Integer ano) {
        System.out.println();
        System.out.println("| -- Total de alarmes para o veiculo '" + matricula + "' no ano '" + ano + "' -- |");
        System.out.println("| Matricula | Nr Alarmes |");
        System.out.println("| -------------------------------------------------------------- |");
        for (TotalDeAlarmesResult row : table) {
            System.out.print("| ");
            System.out.print(row.matricula);
            System.out.print("  |  ");
            System.out.print(row.n_of_alarmes);
            System.out.println(" |");
        }
        System.out.println("| -------------------------------------------------------------- |");
    }
}
