package BusinessLogic.Handlers.d;

import Utils.UIUtils;
import Utils.UIUtils.Input.Parameter;
import model.InputValidators;

import java.util.ArrayList;

import static Utils.Utils.CallStoredProcedure;

public class InsertClienteParticular {

    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        Parameter nif = new Parameter("Nif", InputValidators::NIF);
        Parameter cc = new Parameter("CC", InputValidators::CC);
        Parameter nome = new Parameter("Nome", InputValidators::NOME);
        Parameter morada = new Parameter("Morada", InputValidators::MORADA);
        Parameter id_referenciador = new Parameter("Id Referenciador",InputValidators::NIF );
        Parameter telefone = new Parameter("Telefone", InputValidators::TELEFONE )
        UIUtils.Input.getMultipleInputs(new ArrayList<>() {
            {add(nif); add(cc); add(nome); add(morada); add(id_referenciador); add(telefone);}
        });

        System.out.println(nif.value);
        System.out.println(cc.value);
        System.out.println(nome.value);
        System.out.println(morada.value);
        System.out.println(id_referenciador.value);

        Object[] args = {nif, cc, nome, morada, id_referenciador,telefone};
        CallStoredProcedure("insert_cliente_particular", args);

    }
}
