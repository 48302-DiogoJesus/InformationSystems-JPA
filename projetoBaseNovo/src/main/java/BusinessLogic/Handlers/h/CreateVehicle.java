package BusinessLogic.Handlers.h;

import Utils.UI_Utils;
import static Utils.Utils.CallProcedure;

import model.EntityParameters;
import Utils.Utils.Parameter;

import java.util.ArrayList;

public class CreateVehicle {

    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() {
        Parameter<String> matricula = EntityParameters.MATRICULA(false);
        Parameter<String> idCliente = EntityParameters.NIF(false);
        Parameter<Integer> idGps = EntityParameters.GPSID(false);
        Parameter<String> estadoGps = EntityParameters.ESTADOGPS(false);
        Parameter<String> nomeCondutor = EntityParameters.NOMECONDUTOR(false);
        Parameter<String> telefoneCondutor = EntityParameters.TELEFONE(false);
        Parameter<Integer> numAlarmes = EntityParameters.NUMALARMES(false);

        UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(matricula); add(idCliente); add(idGps); add(estadoGps);
                add(nomeCondutor); add(telefoneCondutor); add(numAlarmes);}
        });

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
