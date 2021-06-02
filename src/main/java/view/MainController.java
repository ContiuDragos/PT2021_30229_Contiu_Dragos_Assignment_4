package view;

import BusinessLayer.DeliveryService;
import BusinessLayer.Serializator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainController {
    private MainGUI mainGUI;
    private DeliveryService deliveryService;
    private Serializator serializator = new Serializator();

    public MainController(MainGUI mainGUI)
    {
        this.mainGUI = mainGUI;
        try {
            deliveryService = serializator.deserialize("serialize.txt");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        ///deliveryService.addInitialUsers();
        mainGUI.addLogInListener(new LogInListener());
        mainGUI.frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                serializator.serialize(deliveryService,"serialize.txt");
            }
        });
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
                    ///mainGUI.frame.dispose();
                    new AdminController(new AdminGUI(),deliveryService);
                }
                else
                {
                    if(role.equals("Client"))
                    {
                        ///mainGUI.frame.dispose();
                        new ClientController(new ClientGUI(),deliveryService);
                    }
                    else
                    {
                        WorkerController workerController= new WorkerController(new WorkerGUI(),deliveryService);
                        deliveryService.addObserver(workerController);
                    }
                }
            }
            else
                System.out.println("Nu e bun");
        }
    }

}
