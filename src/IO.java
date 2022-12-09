import java.util.ArrayList;

public interface IO {
    void setup();
    void saveAccountData();
    Account loadAccountData();
    ArrayList<City> loadCities();
    MenuCard loadMenuCard();
}
