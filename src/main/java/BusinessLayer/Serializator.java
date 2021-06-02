package BusinessLayer;

import java.io.*;

public class Serializator {

    public Serializator()
    {

    }
    public void serialize(DeliveryService deliveryService, String name) {
        try {
            FileOutputStream file = new FileOutputStream(name);
            ObjectOutputStream write = new ObjectOutputStream(file);

            write.writeObject(deliveryService);
            write.close();
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DeliveryService deserialize(String name) {
        DeliveryService deliveryService = null;
        try {
            FileInputStream file = new FileInputStream(name);
            System.out.println(file.getChannel().size());
            if (file.getChannel().size() != 0) {
                ObjectInputStream read = new ObjectInputStream(file);
                deliveryService = (DeliveryService)read.readObject();
                read.close();
                file.close();
            }
            else
            {
                deliveryService = new DeliveryService();
                deliveryService.addInitialUsers();
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return deliveryService;
    }
}
