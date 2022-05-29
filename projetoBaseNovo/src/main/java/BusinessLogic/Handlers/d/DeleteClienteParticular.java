package BusinessLogic.Handlers.d;

import Utils.UIUtils;
import model.InputValidators;

import java.util.ArrayList;

import static Utils.Utils.CallStoredProcedure;

public class DeleteClienteParticular {
    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        UIUtils.Input.Parameter nif = new UIUtils.Input.Parameter("Nif", InputValidators::NIF);


        UIUtils.Input.getMultipleInputs(new ArrayList<>() {
            {add(nif);}
        });

        System.out.println(nif.value);

        Object[] args = {nif};
        CallStoredProcedure("remove_cliente_particular", args);

    }
}
