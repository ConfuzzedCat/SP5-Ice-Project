import org.w3c.dom.Text;

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
                    ArrayList<City> cities = new ArrayList<>(Arrays.asList(City.values()));
                    cities.remove(City.NONE);
                    String buffer = "";
                    for(City c : cities){
                        buffer += c.toString()+", ";
                    }
                    buffer = buffer.substring(0,buffer.length()-2);

                    TextUI.sendMessage(buffer);
                    ArrayList<String> restaurants;
                    do {
                        String input = TextUI.getUserInput("Pick a city");
                        City searchCity = City.findCity(input);
                        restaurants = Main.getIo().returnRestaurantsOfCity(searchCity);
                        if(restaurants == null || restaurants.size() == 0){
                            TextUI.sendMessage("City not found, try again.");
                        }
                    }while (restaurants == null || restaurants.size() == 0);
                    buffer = "";
                    for(String s : restaurants){
                        buffer += s + ", ";
                    }
                    buffer = buffer.substring(0,buffer.length()-2);
                    TextUI.sendMessage(buffer);
                    MenuCard menu;
                    do {
                        String input = TextUI.getUserInput("Pick a restaurant");
                        menu = Main.getIo().loadMenuCard(input);
                        if(menu == null || menu.getDishes().size() == 0){
                            TextUI.sendMessage("Restaurant not found, try again.");
                        }
                    }while (menu == null || menu.getDishes().size() == 0);
                    buffer = "";
                    for(Dish d : menu.getDishes()){
                        buffer += d.toString() + "\n";
                    }
                    buffer = buffer.substring(0,buffer.length()-1);
                    TextUI.sendMessage(buffer);
                    ArrayList<Dish> dishes = new ArrayList<>();
                    String input = "";
                    do {
                        input = TextUI.getUserInput("What do you want to order? When you're done ordering type: order");
                        if(!input.equalsIgnoreCase("order")) {
                            Dish d = menu.findDish(input);
                            if(d != null) {
                                dishes.add(d);
                            }else{
                                TextUI.sendMessage("Dish not found, try again.");
                            }
                        }
                    } while(!input.equalsIgnoreCase("order"));

                    new CheckOut().checkoutMessage(dishes);

                    tryAgain = false;
                    break;
                case 3:
                    TextUI.sendMessage("Support");
                    TextUI.sendMessage("For at kontakte os på support, kan du enten:\nRinge til os på: +45 00 00 00 00\nEller sende en email på: noreply@FiveStarsOnly.com");
                    showMenu();
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