package Presentation;

import BusinessLogic.BLService;

public class OnTrackApp
{
	public static void main(String[] args)
    {
        BLService bl = new BLService();
        bl.insertClienteParticular(
                "999999990",
                "191919191919",
                "Alberto Couve",
                "Rua das Ruas da Rua do Rio",
                "222222222",
                "555555555"
        );
    }
}
