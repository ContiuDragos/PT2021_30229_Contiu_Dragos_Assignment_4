import BusinessLayer.DeliveryService;
import view.AdminController;
import view.AdminGUI;
import view.MainController;
import view.MainGUI;

public class Main {

    public static void main(String[] args)
    {
        new AdminController(new AdminGUI(),new DeliveryService());
    }
}
