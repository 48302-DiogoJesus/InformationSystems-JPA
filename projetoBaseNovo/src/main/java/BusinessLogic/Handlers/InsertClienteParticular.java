package BusinessLogic.Handlers;

import Utils.UIUtils;
import Utils.UIUtils.Input.Parameter;
import model.InputValidators;

import java.util.ArrayList;

public class InsertClienteParticular {

    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        Parameter nif = new Parameter("Nif", InputValidators::NIF);
        Parameter cc = new Parameter("CC");
        Parameter nome = new Parameter("Nome");
        Parameter morada = new Parameter("Morada");
        Parameter id_referenciador = new Parameter("Id Referenciador", true);
        Parameter telefone = new Parameter("Telefone");

        UIUtils.Input.getMultipleInputs(new ArrayList<>() {
            {add(nif); add(cc); add(nome); add(morada); add(id_referenciador); add(telefone);}
        });

        System.out.println(nif.value);
        System.out.println(cc.value);
        System.out.println(nome.value);
        System.out.println(morada.value);
        System.out.println(id_referenciador.value);
        /*
        Object[] args = {nif, cc, nome, morada, id_referenciador, telefone};
        Utils.CallStoredProcedure("insert_cliente_particular", args);
        */
    }
}
