package BusinessLogic.Handlers.f;

import Utils.Utils.*;
import Utils.*;

import static Utils.Utils.CallProcedure;

public class HandleRegistos {

    public static void run() throws Exception {
        CallProcedure("handle_registos", new Parameter[] {}, Utils.ProcedureType.STORED_PROCEDURE, Utils.ReturnType.VOID);

        System.out.println("[DONE] Registos Processados");
    }
}
