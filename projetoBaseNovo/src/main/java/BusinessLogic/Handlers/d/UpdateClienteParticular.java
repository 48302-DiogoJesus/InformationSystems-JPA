package BusinessLogic.Handlers.d;

import Utils.UIUtils;
import model.InputValidators;

import java.util.ArrayList;

import static Utils.Utils.CallStoredProcedure;
import static com.sun.tools.xjc.reader.Ring.add;


public class UpdateClienteParticular {

    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        UIUtils.Input.Parameter nif = new UIUtils.Input.Parameter("Nif", InputValidators::NIF);
        UIUtils.Input.Parameter cc = new UIUtils.Input.Parameter("CC", InputValidators::CC);
        UIUtils.Input.Parameter nome = new UIUtils.Input.Parameter("Nome", InputValidators::NOME);
        UIUtils.Input.Parameter morada = new UIUtils.Input.Parameter("Morada", InputValidators::MORADA);
        UIUtils.Input.Parameter id_referenciador = new UIUtils.Input.Parameter("Id Referenciador",InputValidators::NIF );

        UIUtils.Input.getMultipleInputs(new ArrayList<>() {
            {add(nif); add(cc); add(nome); add(morada); add(id_referenciador); }
        });

        System.out.println(nif.value);
        System.out.println(cc.value);
        System.out.println(nome.value);
        System.out.println(morada.value);
        System.out.println(id_referenciador.value);

        Object[] args = {nif, cc, nome, morada, id_referenciador};
        CallStoredProcedure("insert_cliente_particular", args);

    }


}

