package BusinessLogic.Handlers.h;

import Utils.UI_Utils;
import static Utils.Utils.CallProcedure;

import model.EntityParameters;
import Utils.Utils.Parameter;

import java.util.ArrayList;

public class CreateVehicle {

    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        Parameter matricula = EntityParameters.MATRICULA(false, false);
        Parameter idCliente = EntityParameters.NIF(false, true);
        Parameter idGps = EntityParameters.GPSID(false, true);
        Parameter estadoGps = EntityParameters.ESTADOGPS(false, true);
        Parameter nomeCondutor = EntityParameters.NOMECONDUTOR(false);
        Parameter telefoneCondutor = EntityParameters.TELEFONE(false);
        Parameter numAlarmes = EntityParameters.NUMALARMES(false);

        Boolean result = UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(matricula); add(idCliente); add(idGps); add(estadoGps);
                add(nomeCondutor); add(telefoneCondutor); add(numAlarmes);}
        });

        if (!result)
            return;

        Parameter[] args = { matricula, idCliente, idGps, estadoGps,
                nomeCondutor,telefoneCondutor, numAlarmes };
        CallProcedure(
                "create_veiculo",
                args,
                Utils.Utils.ProcedureType.STORED_PROCEDURE,
                Utils.Utils.ReturnType.VOID
        );
    }
}
