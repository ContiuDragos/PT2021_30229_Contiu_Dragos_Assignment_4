package BusinessLayer;

import java.util.Calendar;

public class Order {
    private final int orderID;
    private final int clientID;
    private final Calendar date;

    public Order(int orderID, int clientID, Calendar date)
    {
        this.clientID=clientID;
        this.orderID=orderID;
        this.date=date;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public Calendar getDate() {
        return date;
    }

}
