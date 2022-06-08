package BusinessLogic.Handlers.h;

import Utils.UI_Utils;
import static Utils.Utils.CallProcedure;

import model.EntityParameters;
import Utils.Utils.Parameter;

import java.util.ArrayList;

public class CreateVehicleWithProcedure {

    // IGNORE ARGS FOR NOW, MAYBE REMOVE LATER
    public static void run() throws Exception {
        Parameter matricula = EntityParameters.MATRICULA(false, false);
        Parameter idCliente = EntityParameters.NIF(false, true);
        Parameter idGps = EntityParameters.GPSID(false, true);
        Parameter estadoGps = EntityParameters.ESTADOGPS(false, true);
        Parameter nomeCondutor = EntityParameters.NOMECONDUTOR(false);
        Parameter telefoneCondutor = EntityParameters.TELEFONE(false);
        Parameter numAlarmes = EntityParameters.NUMALARMES(false);
        Parameter longitude = EntityParameters.LONGITUDE(true);
        Parameter latitude = EntityParameters.LATITUDE(true);
        Parameter raio = EntityParameters.RAIO(true);

        Boolean result = UI_Utils.getMultipleInputs(new ArrayList<>() {
            {add(matricula); add(idCliente); add(idGps); add(estadoGps);
                add(nomeCondutor); add(telefoneCondutor); add(numAlarmes);
                add(longitude); add(latitude); add(raio);}
        });

        if (!result)
            return;

        Parameter[] args = { matricula, idCliente, idGps, estadoGps,
                nomeCondutor,telefoneCondutor, numAlarmes, longitude, latitude, raio };

        CallProcedure(
                "create_veiculo",
                args,
                Utils.Utils.ProcedureType.STORED_PROCEDURE,
                Utils.Utils.ReturnType.VOID
        );

        System.out.println("[DONE] Veículo criado");
    }
}
