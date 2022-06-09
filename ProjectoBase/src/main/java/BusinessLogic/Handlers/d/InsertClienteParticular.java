package BusinessLogic.Handlers.d;

import Utils.UI_Utils;
import static Utils.Utils.CallProcedure;
import Utils.Utils.ProcedureType;
import Utils.Utils.ReturnType;
import model.EntityParameters;
import Utils.Utils.Parameter;

import java.util.ArrayList;


public class InsertClienteParticular {

    public static void run() throws Exception {
        Parameter nif = EntityParameters.NIF(false, false);
        Parameter cc = EntityParameters.CC(false);
        Parameter nome = EntityParameters.NOME(false);
        Parameter morada = EntityParameters.MORADA(false);
        Parameter telefone = EntityParameters.TELEFONE(false);
        Parameter id_referenciador = EntityParameters.REFERENCIADOR(true, true);

        Boolean result = UI_Utils.getMultipleInputs(new ArrayList<>() {
            {
                add(nif);
                add(cc);
                add(nome);
                add(morada);
                add(id_referenciador);
                add(telefone);
            }
        });

        if (!result)
            return;

        Parameter[] args = {nif, cc, nome, morada, id_referenciador, telefone};
        CallProcedure(
                "insert_cliente_particular",
                args,
                ProcedureType.STORED_PROCEDURE,
                ReturnType.VOID
        );
        System.out.println("[DONE] Cliente Inserido");
    }
}
