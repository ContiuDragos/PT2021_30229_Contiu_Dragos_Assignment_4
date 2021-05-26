package BusinessLayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Bill {
    private ArrayList<MenuItem> products;
    private Order order;
    private FileWriter myWriter;
    private File myFile;
    private static int nrBill = 0;

    public Bill(ArrayList<MenuItem> products, Order order) {
        this.products = products;
        this.order=order;
        int total=0;
        try {
            myFile = new File("bill" + (++nrBill) + ".txt");
            myWriter = new FileWriter("bill" + nrBill + ".txt");

            myWriter.write("Bill nr."+nrBill+"\n");
            myWriter.write("Date: "+order.getDate()+"\n");
            myWriter.write("Order id."+order.getOrderID()+"\n");
            myWriter.write("Client id."+order.getClientID()+"\n");
            myWriter.write("Products\n");
            myWriter.flush();
            for(MenuItem i : products) {
                myWriter.write(i.getTitle() + "......." + i.computePrice() + "Euro\n");
                myWriter.flush();
                total+=i.computePrice();
            }
            myWriter.write("Total: "+total+" Euro");
            myWriter.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
