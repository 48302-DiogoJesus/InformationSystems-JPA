package BusinessLogic.Handlers.d;

import Utils.UIUtils.*;
import static Utils.Utils.CallProcedure;
import Utils.Utils.ProcedureType;
import Utils.Utils.ReturnType;
import model.Parameters.*;

import java.util.ArrayList;

public class InsertClienteParticular {

    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        Parameter nif = Parameters.NIF();
        Parameter cc = Parameters.CC();
        Parameter nome = Parameters.NOME();
        Parameter morada = Parameters.MORADA();
        Parameter<String> telefone = Parameters.TELEFONE();
        Parameter id_referenciador = Parameters.NIF();

        Input.getMultipleInputs(new ArrayList<>() {
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
