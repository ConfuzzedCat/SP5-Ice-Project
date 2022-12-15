import java.util.ArrayList;

public class MenuCard {
    // Klassen skal holde på en arrayliste af Dish, som er resturantens retter
    private ArrayList<Dish> dishes;
    private String restaurantName;

    public ArrayList<Dish> getDishes() {

        return dishes;
    }
    public String getRestaurantName() {

        return restaurantName;
    }
    public MenuCard(ArrayList<Dish> dishes, String restaurantName) {
        this.dishes = dishes;
        this.restaurantName = restaurantName;
    }

    public Dish findDish(String dishName){
        for (Dish d : dishes ) {
        if(d.getName().equalsIgnoreCase(dishName)){
            return d;
        }
        }


        return null;
    }

    @Override
    public String toString() {
        return "MenuCard{" + "dishes=" + dishes + ", restaurantName='" + restaurantName + '\'' + '}'; //TODO gør pænt
    }
}
