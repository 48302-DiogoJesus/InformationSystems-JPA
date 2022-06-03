package BusinessLogic.Handlers.d;

import Utils.UI_Utils;
import Utils.Utils.ProcedureType;
import Utils.Utils.ReturnType;
import java.util.ArrayList;
import Utils.Utils.Parameter;

import model.EntityParameters;

import static Utils.Utils.CallProcedure;

public class RemoveClienteParticular {
    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        Parameter nif = EntityParameters.NIF(false);

        UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(nif);}
        });

        Parameter[] args = { nif };
        CallProcedure(
                "remove_cliente_particular",
                args,
                ProcedureType.STORED_PROCEDURE,
                ReturnType.VOID
        );
    }
}
