package BusinessLogic.Handlers.f;

import Utils.UIUtils;
import Utils.Utils;
import model.InputValidators;

import java.util.ArrayList;

import static Utils.Utils.CallProcedure;

public class HandleRegistos {
    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        String[] args = {};
        CallProcedure("handle_registos", args, Utils.ProcedureType.STORED_PROCEDURE, Utils.ReturnType.VOID);
        System.out.println("Sucesso");
    }
}
