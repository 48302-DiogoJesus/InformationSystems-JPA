package BusinessLogic.Handlers.d;

import Utils.UIUtils;
import Utils.Utils.ProcedureType;
import model.InputValidators;

import java.util.ArrayList;

import static Utils.Utils.CallProcedure;

public class RemoveClienteParticular {
    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        UIUtils.Input.Parameter nif = new UIUtils.Input.Parameter("Nif", InputValidators::NIF);

        UIUtils.Input.getMultipleInputs(new ArrayList<>() {
            {add(nif);}
        });

        String[] args = { nif.value };
        CallProcedure("remove_cliente_particular", args, ProcedureType.STORED_PROCEDURE);
    }
}
