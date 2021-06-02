package view;

import BusinessLayer.DeliveryService;

import java.util.Observable;
import java.util.Observer;

public class WorkerController implements Observer {

    private final WorkerGUI workerGUI;
    private final DeliveryService deliveryService;

    public WorkerController(WorkerGUI workerGUI, DeliveryService deliveryService)
    {
        this.workerGUI=workerGUI;
        this.deliveryService=deliveryService;
        deliveryService.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("In worker");
        String message = (String)o;
        workerGUI.addOrders(message);
        System.out.println("Aici");
    }
}
