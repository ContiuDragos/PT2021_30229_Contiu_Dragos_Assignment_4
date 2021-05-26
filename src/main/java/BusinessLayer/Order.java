package BusinessLayer;

import java.util.Date;

public class Order {
    private final int orderID;
    private final int clientID;
    private final Date date;
    private int total;

    public Order(int orderID, int clientID, Date date)
    {
        this.clientID=clientID;
        this.orderID=orderID;
        this.date=date;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int hashCode()
    {
        return clientID+orderID;
    }

}
