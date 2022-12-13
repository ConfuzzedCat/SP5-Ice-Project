import java.util.ArrayList;

public interface IO {
    void setup();
    void saveAccountData();
    ArrayList<Account> loadAccountData();
    ArrayList<City> loadCities();
    MenuCard loadMenuCard(String r);
}
