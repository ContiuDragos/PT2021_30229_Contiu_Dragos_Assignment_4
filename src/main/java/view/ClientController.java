package view;

import BusinessLayer.DeliveryService;

public class ClientController {
    private ClientGUI clientGUI;
    private DeliveryService deliveryService;

    public ClientController(ClientGUI clientGUI, DeliveryService deliveryService)
    {
        this.clientGUI=clientGUI;
        this.deliveryService=deliveryService;
    }
}
