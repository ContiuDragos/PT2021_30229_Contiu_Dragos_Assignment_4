package BusinessLayer;

public interface MenuItem {
    int computePrice();
    int getId();
    String getTitle();
    float getRating();
    int getFat();
    int getCalories();
    int getProtein();
    int getPrice();
    int getSodium();
    int getTimesOrdered();
    void setTimesOrdered(int counter);
}
