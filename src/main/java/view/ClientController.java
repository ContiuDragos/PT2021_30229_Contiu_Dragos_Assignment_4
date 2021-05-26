package view;

import BusinessLayer.DeliveryService;
import Model.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientController {
    private ClientGUI clientGUI;
    private DeliveryService deliveryService;
    private int counter;

    public ClientController(ClientGUI clientGUI, DeliveryService deliveryService)
    {
        this.clientGUI=clientGUI;
        this.deliveryService=deliveryService;
        deliveryService.importProducts();
        clientGUI.addProducts(deliveryService.getMenuItems());
        clientGUI.addAddListener(new AddListener());
        clientGUI.submitListener(new SubmitListener());
    }

    class AddListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            clientGUI.addToLabels(++counter,0);
        }
    }

    class SubmitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            counter = 0;
            clientGUI.addToLabels(0,0);
        }
    }
}
