package BusinessLayer;

public class BaseProduct implements MenuItem{
    private final String title;
    private final float rating;
    private final int calories;
    private final int protein;
    private final int fat;
    private final int sodium;
    private final int price;

    public BaseProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price)
    {
        this.title=title;
        this.rating=rating;
        this.calories=calories;
        this.protein=protein;
        this.fat=fat;
        this.sodium=sodium;
        this.price=price;
    }
    @Override
    public int computePrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public int getPrice() {
        return price;
    }
}
