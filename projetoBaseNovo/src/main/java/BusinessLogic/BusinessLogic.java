package BusinessLogic;

import BusinessLogic.Handlers.Handler;
import BusinessLogic.Handlers.d.RemoveClienteParticular;
import BusinessLogic.Handlers.d.InsertClienteParticular;
import BusinessLogic.Handlers.d.UpdateClienteParticular;
import BusinessLogic.Handlers.e.TotalDeAlarmes;
import BusinessLogic.Handlers.f.HandleRegistos;
import BusinessLogic.Handlers.h.CreateVehicle;
import BusinessLogic.Handlers.j.InserirSobreVistaAlarmes;
import BusinessLogic.Handlers.others.InsertEstadoGPS;
import BusinessLogic.Handlers.i.ListAlarmes;
import BusinessLogic.Handlers.others.ListEstadosGPS;
import BusinessLogic.Handlers.others.RemoveEstadoGPS;

public class BusinessLogic
{
    // d)
    static public Handler removeClienteParticular = RemoveClienteParticular::run;
    static public Handler insertClienteParticular = InsertClienteParticular::run;
    static public Handler updateClienteParticular = UpdateClienteParticular::run;

    // e)
    static public Handler totalDeAlarmes = TotalDeAlarmes::run;

    // f)
    static public Handler handleRegistos = HandleRegistos::run;

    // h)
    static public Handler createVehicle = CreateVehicle::run;

    // Estados GPS
    static public Handler insertEstadoGPS = InsertEstadoGPS::run;
    static public Handler listEstadoGPS = ListEstadosGPS::run;
    static public Handler removeEstadoGPS = RemoveEstadoGPS::run;

    // Alarmes
    static public Handler listAlarmes = ListAlarmes::run;

    // j)
    static public Handler inserirSobreVistaAlarmes = InserirSobreVistaAlarmes::run;
}
