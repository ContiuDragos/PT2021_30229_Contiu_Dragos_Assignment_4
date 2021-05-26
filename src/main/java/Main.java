import BusinessLayer.DeliveryService;
import view.*;

import java.util.Calendar;

public class Main {

    public static void main(String[] args)
    {
        ///new AdminController(new AdminGUI(),new DeliveryService());
        new ClientController(new ClientGUI(), new DeliveryService());
    }
}
