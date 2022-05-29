package BusinessLogic.Handlers.d;

import Utils.UIUtils.Input;
import Utils.Utils.ProcedureType;
import Utils.Utils.ReturnType;
import java.util.ArrayList;
import model.Parameters.*;

import static Utils.Utils.CallProcedure;

public class RemoveClienteParticular {
    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        Parameter nif = Parameters.NIF();

        Input.getMultipleInputs(new ArrayList<>() {
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
