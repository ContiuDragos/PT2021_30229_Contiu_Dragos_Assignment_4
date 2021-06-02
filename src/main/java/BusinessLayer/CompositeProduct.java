package BusinessLayer;


import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct implements MenuItem, Serializable {

    ArrayList<BaseProduct> products = new ArrayList<>();
    private String title;
    private int id;
    private int timesOrdered;

    public CompositeProduct(String title, int id)
    {
        this.title=title;
        this.id=id;
    }

    @Override
    public int computePrice() {
        int sum=0;
        for(MenuItem i : products)
            sum+=i.computePrice();
        return sum;
    }

    public ArrayList<BaseProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<BaseProduct> products) {
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void add(BaseProduct menuItem)
    {
        products.add(menuItem);
        title += " "+menuItem.getTitle();
    }

    public int getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }

    public float getRating()
    {
        float rating = 0;
        for(BaseProduct i : products)
            rating+= i.getRating();
        rating/=products.size();
        return rating;
    }

    public int getCalories()
    {
        int calories = 0;
        for(BaseProduct i : products)
            calories+= i.getCalories();
        return calories;
    }

    public int getProtein()
    {
        int protein = 0;
        for(BaseProduct i : products)
            protein+= i.getProtein();
        return protein;
    }

    public int getFat()
    {
        int fat = 0;
        for(BaseProduct i : products)
            fat+= i.getFat();
        return fat;
    }

    public int getSodium()
    {
        int sodium = 0;
        for(BaseProduct i : products)
            sodium+= i.getSodium();
        return sodium;
    }
    public int getPrice()
    {
        int price = 0;
        for(BaseProduct i : products)
            price+= i.getPrice();
        return price;
    }
}
