package Model;

public class Client extends User{

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
