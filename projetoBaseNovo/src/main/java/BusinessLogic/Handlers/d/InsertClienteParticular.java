package BusinessLogic.Handlers.d;

import Utils.UI_Utils;
import static Utils.Utils.CallProcedure;
import Utils.Utils.ProcedureType;
import Utils.Utils.ReturnType;
import model.EntityParameters;
import Utils.Utils.Parameter;

import java.util.ArrayList;

public class InsertClienteParticular {

    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        Parameter nif = EntityParameters.NIF(false);
        Parameter cc = EntityParameters.CC(false);
        Parameter nome = EntityParameters.NOME(false);
        Parameter morada = EntityParameters.MORADA(false);
        Parameter<String> telefone = EntityParameters.TELEFONE(false);
        Parameter id_referenciador = EntityParameters.NIF(false);

        UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(nif); add(cc); add(nome); add(morada); add(id_referenciador); add(telefone);}
        });

        Parameter[] args = { nif, cc, nome, morada, id_referenciador,telefone };
        CallProcedure(
                "insert_cliente_particular",
                args,
                ProcedureType.STORED_PROCEDURE,
                ReturnType.VOID
        );
    }
}
