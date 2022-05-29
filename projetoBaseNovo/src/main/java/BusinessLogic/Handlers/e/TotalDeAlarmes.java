package BusinessLogic.Handlers.e;

import Utils.Utils;

import java.util.ArrayList;
import java.util.List;

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
        // Ask matricula
        String[] args = new String[] {"2022", "JD-23-09"};

        List<Object[]> list = CallProcedure("count_alarmes_returns_table", args, Utils.ProcedureType.FUNCTION);

        ArrayList<TotalDeAlarmesResult> resultsList = new ArrayList<>(list.size()) {};

        for (Object[] item : list) {
            resultsList.add(new TotalDeAlarmesResult((String) item[0], (Long) item[1]));
        }

        // Print result
    }
}
