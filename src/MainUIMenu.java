import java.util.ArrayList;
import java.util.Arrays;

public class MainUIMenu implements UIMenu {
    //Test

    public MainUIMenu() {

        this.showMenu();
    }

    @Override
    public void goBack() {

        Main.setCurrentMenu(new StartMenu());
    }

    public void showMenu() {
        TextUI.sendMessage("\nVelkommen til 5 Stars Only!\n");

        //Choose user currentAccount
        Account a = Main.getCurrentAccount();

        ArrayList<String> list = new ArrayList<>(Arrays.asList("Konto-indstillinger", "Restauranter", "Support", "Log ud"));

        boolean tryAgain = true;

        while (tryAgain) {
            int choice = TextUI.getUserInput("Vælg venligst: ", list);
            switch (choice) {
                case 1:
                    TextUI.sendMessage("Konto-indstillinger");
                    TextUI.sendMessage("Account settings is not available at this moment.");
                    showMenu();
                case 2:
                    TextUI.sendMessage("Restauranter");
                    String buffer = "";
                    for(City c : City.values()){
                        buffer += c.toString()+", "; // TODO gør pænt
                    }
                    TextUI.sendMessage(buffer);
                    String input = TextUI.getUserInput("Pick a city");
                    City searchCity = City.findCity(input);
                    ArrayList<String> restaurants = Main.getIo().returnRestaurantsOfCity(searchCity);

                    buffer = "";
                    for(String s : restaurants){
                        buffer += s + ", "; // TODO gør pænt
                    }
                    TextUI.sendMessage(buffer);
                    input = TextUI.getUserInput("Pick a restaurant");
                    MenuCard menu = Main.getIo().loadMenuCard(input);

                    buffer = "";
                    for(Dish d : menu.getDishes()){
                        buffer += d.toString() + ", "; // TODO gør pænt
                    }
                    TextUI.sendMessage(buffer);
                    input = TextUI.getUserInput("What do you want to order?");
                    ArrayList<Dish> dishes = new ArrayList<>();
                    dishes.add(menu.findDish(input));
                    new CheckOut().checkoutMessage(dishes);

                    tryAgain = false;
                    break;
                case 3:
                    TextUI.sendMessage("Support");
                    new StartMenu(); // logout
                    tryAgain = false;
                    break;
                default:
                    TextUI.sendMessage("Log ud");
                    goBack();
                    break;

            }
        }
    }
}