package BusinessLogic.Handlers.i;

import Utils.Utils.*;

import java.util.List;

import model.Entities.Views.ListAllAlarmes;

import static Utils.UI_Utils.printTable;
import static Utils.Utils.CallProcedure;

public class ListAlarmes {
    public static void run() throws Exception {

        Parameter[] args = {};

        List<Object[]> list = CallProcedure(
                "list_all_alarmes",
                args,
                ProcedureType.VIEW,
                ReturnType.TABLE
        );

        // TODO: Print the table
        printTable("Vista de Alarmes", list, ListAllAlarmes.class);
    }
}
