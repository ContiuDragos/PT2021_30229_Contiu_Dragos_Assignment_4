package BusinessLayer;


import java.util.ArrayList;

public class CompositeProduct implements MenuItem {

    ArrayList<MenuItem> products = new ArrayList<>();
    @Override
    public int computePrice() {
        int sum=0;
        for(MenuItem i : products)
            sum+=i.computePrice();
        return sum;
    }

    public void add(MenuItem menuItem)
    {
        products.add(menuItem);
    }
}
