import java.util.ArrayList;

public interface IO {
    void setup();
    void saveAccountData(ArrayList<Account> accounts);
    ArrayList<Account> loadAccountData();
    ArrayList<City> loadCities();
    MenuCard loadMenuCard();
}
