package Model;

import java.io.Serializable;

public class Client extends User implements Serializable {

    private int nrOrders;
    public Client(int id,String username, String password)
    {
        super(id,username,password);
    }

    public int getNrOrders() {
        return nrOrders;
    }

    public void setNrOrders(int nrOrders) {
        this.nrOrders = nrOrders;
    }
}
