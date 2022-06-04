package BusinessLogic.Handlers.e;

import Utils.Utils;
import Utils.UI_Utils;
import java.util.ArrayList;
import java.util.List;

import Utils.Utils.ReturnType;
import Utils.Utils.Parameter;
import model.Entities.Alarme;
import model.EntityParameters;

import static Utils.UI_Utils.printTable;
import static Utils.Utils.CallProcedure;

public class TotalDeAlarmes {

    public static void run() throws Exception {
        // Ask year
        Parameter ano = EntityParameters.ANO(false);
        // Ask matricula
        Parameter matricula = EntityParameters.MATRICULA(true, true);

        Boolean result = UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(ano); add(matricula);}
        });

        if (!result)
            return;

        Parameter[] args = { ano, matricula };

        List<Object[]> list = CallProcedure(
            "count_alarmes_returns_table",
            args,
            Utils.ProcedureType.FUNCTION,
            ReturnType.TABLE
        );

        printTable("Alarmes", list, Alarme.class);
    }
}
