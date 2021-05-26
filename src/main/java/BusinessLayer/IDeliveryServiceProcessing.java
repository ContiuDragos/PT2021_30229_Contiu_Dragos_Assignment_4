package BusinessLayer;

import Model.Client;

import java.util.ArrayList;
import java.util.List;

public interface IDeliveryServiceProcessing {

    void importProducts();

    boolean addProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price);

    boolean deleteProduct(String title);

    boolean modifyProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price);

    void createCompositeProduct(ArrayList<BaseProduct> e, String title);

    List<Order> generateTimeReport(int startTime, int stopTime);

    List<MenuItem> generateProductOrderedMoreReport(int minimum);

    List<Client> generateClientWithMinOrders(int minimumOrders, int minimumValue);

    List<MenuItem> generateProductsWithinASpecifiedDay(int day);

    void addOrder();

    List<MenuItem> searchItemByClient(String title, float rating, int fat, int protein, int calories, int sodium, int price);

}
