package BusinessLogic.Handlers.d;

import Utils.UIUtils;
import Utils.UIUtils.Input.*;
import Utils.Utils;
import model.InputValidators;
import static Utils.Utils.CallProcedure;
import Utils.Utils.ProcedureType;
import Utils.Utils.ReturnType;

import java.util.ArrayList;

public class InsertClienteParticular {

    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        /*
        Parameter nif = new Parameter("Nif", InputValidators::NIF);
        Parameter cc = new Parameter("CC", InputValidators::CC);
        Parameter nome = new Parameter("Nome", InputValidators::NOME);
        Parameter morada = new Parameter("Morada", InputValidators::MORADA);
        Parameter id_referenciador = new Parameter("Id Referenciador",InputValidators::NIF);
        Parameter telefone = new Parameter("Telefone", InputValidators::TELEFONE);

        UIUtils.Input.getMultipleInputs(new ArrayList<>() {
            {add(nif); add(cc); add(nome); add(morada); add(id_referenciador); add(telefone);}
        });

        String[] args = {nif.value, cc.value, nome.value, morada.value, id_referenciador.value,telefone.value};
        CallProcedure(
                "insert_cliente_particular",
                args,
                ProcedureType.STORED_PROCEDURE,
                ReturnType.VOID
        );*/

        String[] args = {};
        CallProcedure("handle_registos", args, Utils.ProcedureType.STORED_PROCEDURE,ReturnType.VOID);
    }
}
