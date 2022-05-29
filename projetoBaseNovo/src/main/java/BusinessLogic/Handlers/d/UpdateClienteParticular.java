package BusinessLogic.Handlers.d;

import Utils.UIUtils.*;
import Utils.Utils.ProcedureType;
import Utils.Utils.ReturnType;
import model.Parameters.*;

import java.util.ArrayList;

import static Utils.Utils.CallProcedure;


public class UpdateClienteParticular {

    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        Parameter nif = Parameters.NIF();
        Parameter cc = Parameters.CC();
        Parameter nome = Parameters.NOME();
        Parameter morada = Parameters.MORADA();
        Parameter id_referenciador = Parameters.NIF();

        Input.getMultipleInputs(new ArrayList<>() {
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

