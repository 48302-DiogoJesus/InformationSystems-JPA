package BusinessLogic.Handlers.e;

import Utils.Utils;
import Utils.UIUtils.*;
import Utils.UIUtils.Input.Parameter;
import model.InputValidators;
import java.util.ArrayList;
import java.util.List;
import Utils.Utils.ReturnType;

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
        Parameter ano = new Parameter("Ano", InputValidators::INTEGER);
        // Ask matricula
        Parameter matricula = new Parameter("Matrícula", InputValidators::MATRICULA);

        Input.getMultipleInputs(new ArrayList<>() {
            {add(ano); add(matricula);}
        });

        String[] args = new String[] {ano.value, matricula.value};

        List<Object[]> list = CallProcedure(
                "count_alarmes_returns_table",
                args,
                Utils.ProcedureType.FUNCTION,
                ReturnType.TABLE
        );

        ArrayList<TotalDeAlarmesResult> resultsList = convertToTable(list);

        System.out.println("| -- Total de alarmes para o veiculo '" + matricula + "' no ano '" + ano + "' -- |");
        for (TotalDeAlarmesResult row : resultsList) {
            System.out.print("Matrícula: " + row.matricula);
            System.out.println("| Nr de Alarmes: " + row.n_of_alarmes);
        }
        System.out.println("| ---- |");
    }

    private static ArrayList<TotalDeAlarmesResult> convertToTable(List<Object[]> list) {
        ArrayList<TotalDeAlarmesResult> resultsList = new ArrayList<>(list.size()) {};
        for (Object[] item : list) {
            resultsList.add(new TotalDeAlarmesResult((String) item[0], (Long) item[1]));
        }
        return resultsList;
    }
}
