package BusinessLayer;

public class Order {
    private int orderID;
    private int clientID;
    private String date;

    public Order(int orderID, int clientID, String date)
    {
        this.clientID=clientID;
        this.orderID=orderID;
        this.date=date;
    }


}
