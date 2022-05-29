package BusinessLogic.Handlers.d;

import Utils.UIUtils;
import model.InputValidators;
import Utils.Utils.ProcedureType;

import java.util.ArrayList;

import static Utils.Utils.CallProcedure;


public class UpdateClienteParticular {

    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        UIUtils.Input.Parameter nif = new UIUtils.Input.Parameter("Nif", InputValidators::NIF);
        UIUtils.Input.Parameter cc = new UIUtils.Input.Parameter("CC", InputValidators::CC);
        UIUtils.Input.Parameter nome = new UIUtils.Input.Parameter("Nome", InputValidators::NOME);
        UIUtils.Input.Parameter morada = new UIUtils.Input.Parameter("Morada", InputValidators::MORADA);
        UIUtils.Input.Parameter id_referenciador = new UIUtils.Input.Parameter("Id Referenciador",InputValidators::NIF);

        UIUtils.Input.getMultipleInputs(new ArrayList<>() {
            {add(nif); add(cc); add(nome); add(morada); add(id_referenciador); }
        });

        String[] args = {nif.value, cc.value, nome.value, morada.value, id_referenciador.value};
        CallProcedure("update_cliente_particular", args, ProcedureType.STORED_PROCEDURE);
    }
}

