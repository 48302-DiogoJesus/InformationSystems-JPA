package BusinessLogic.Handlers.d;

import Utils.UI_Utils;
import Utils.Utils.ProcedureType;
import Utils.Utils.ReturnType;
import model.EntityParameters;
import Utils.Utils.Parameter;

import java.util.ArrayList;

import static Utils.Utils.CallProcedure;


public class UpdateClienteParticular {

    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        Parameter nif = EntityParameters.NIF(false, true);
        Parameter cc = EntityParameters.CC(false);
        Parameter nome = EntityParameters.NOME(false);
        Parameter morada = EntityParameters.MORADA(false);
        Parameter id_referenciador = EntityParameters.NIF(false, true);

        UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(nif); add(cc); add(nome); add(morada); add(id_referenciador); }
        });

        Parameter[] args = { nif, cc, nome, morada, id_referenciador };
        CallProcedure(
                "update_cliente_particular",
                args,
                ProcedureType.STORED_PROCEDURE,
                ReturnType.VOID
        );
    }
}

