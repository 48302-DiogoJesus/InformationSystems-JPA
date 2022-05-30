package BusinessLogic.Handlers.f;

import Utils.Utils.*;
import Utils.*;
import java.util.Scanner;

import static Utils.Utils.CallProcedure;

public class HandleRegistos {
    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        CallProcedure("handle_registos", new Parameter[] {}, Utils.ProcedureType.STORED_PROCEDURE, Utils.ReturnType.VOID);

        System.out.println("Sucesso");
        System.out.println("Press ENTER to continue...");
        Scanner wait = new Scanner(System.in);
        wait.nextLine();
    }
}
