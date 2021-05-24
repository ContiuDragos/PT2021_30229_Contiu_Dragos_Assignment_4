package view;

import BusinessLayer.DeliveryService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private MainGUI mainGUI;
    private DeliveryService deliveryService;

    public MainController(MainGUI mainGUI, DeliveryService deliveryService)
    {
        this.mainGUI = mainGUI;
        this.deliveryService = deliveryService;
        deliveryService.addInitialUsers();
        mainGUI.addLogInListener(new LogInListener());
    }

    class LogInListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String username = mainGUI.getUsername();
            String password = mainGUI.getPassword();
            String role = mainGUI.getRole();

            if(deliveryService.verifyLogIn(role,username,password))
            {
                if(role.equals("Admin"))
                {
                    mainGUI.frame.dispose();
                    new AdminController(new AdminGUI(),deliveryService);
                }
                else
                {
                    if(role.equals("Client"))
                    {
                        mainGUI.frame.dispose();
                        new ClientController(new ClientGUI(),deliveryService);
                    }
                    else
                    {
                        new WorkerGUI();
                    }
                }
            }
            else
                System.out.println("Nu e bun");
        }
    }


}
