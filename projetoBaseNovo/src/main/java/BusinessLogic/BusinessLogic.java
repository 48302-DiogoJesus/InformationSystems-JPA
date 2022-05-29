package BusinessLogic;

import BusinessLogic.Handlers.d.RemoveClienteParticular;
import BusinessLogic.Handlers.d.InsertClienteParticular;
import BusinessLogic.Handlers.d.UpdateClienteParticular;

public class BusinessLogic
{
    // d)
    static public Handler deleteClienteParticular = RemoveClienteParticular::run;
    static public Handler insertClienteParticular = InsertClienteParticular::run;
    static public Handler updateClienteParticular = UpdateClienteParticular::run;
}
