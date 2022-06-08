package BusinessLogic.Handlers.k;

import Utils.Utils.Parameter;

import static Utils.Utils.CallProcedure;

public class DeleteRegistosInvalidos {
    public static void run() throws Exception {
        CallProcedure(
                "remove_reg_invalidos",
                new Parameter[] {},
                Utils.Utils.ProcedureType.STORED_PROCEDURE,
                Utils.Utils.ReturnType.VOID
        );

        System.out.println("[DONE] Registos inválidos apagados");
    }
}
