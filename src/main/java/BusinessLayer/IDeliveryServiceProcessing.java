package BusinessLayer;

import java.util.ArrayList;

public interface IDeliveryServiceProcessing {

    void importProducts();

    boolean addProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price);

    boolean deleteProduct(String title);

    boolean modifyProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price);

    boolean createCompositeProduct(ArrayList<BaseProduct> e, String title);

}
