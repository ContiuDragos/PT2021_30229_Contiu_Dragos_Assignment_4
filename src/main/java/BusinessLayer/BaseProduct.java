package BusinessLayer;

public class BaseProduct implements MenuItem{
    private int id;
    private final String title;
    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public BaseProduct(int id, String title, float rating, int calories, int protein, int fat, int sodium, int price)
    {
        this.id=id;
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

    public int getId() {
        return id;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
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
