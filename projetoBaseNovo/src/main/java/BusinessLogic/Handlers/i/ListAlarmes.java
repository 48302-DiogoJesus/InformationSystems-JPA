package BusinessLogic.Handlers.i;

import Utils.Utils.*;
import java.util.List;
import java.util.Scanner;
import static Utils.Utils.CallProcedure;

public class ListAlarmes {
    public static void run() {

        Parameter[] args = {};

        List<Object[]> list = CallProcedure(
                "list_all_alarmes",
                args,
                ProcedureType.VIEW,
                ReturnType.TABLE
        );


        System.out.println();
        System.out.println("Press ENTER to continue...");
        Scanner wait = new Scanner(System.in);
        wait.nextLine();
    }
}
