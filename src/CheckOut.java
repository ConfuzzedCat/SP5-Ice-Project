import java.util.ArrayList;
import static java.util.UUID.randomUUID;

public class CheckOut {
    // Skal holde den total ordre og give et referencenummer og en adresse til hvor maden skal leveres, til hvis den skal leveres.

    public void checkoutMessage(ArrayList<Dish> checkoutDishes){
    int sum = 0;
    String dishNames = "";
        for (Dish d: checkoutDishes) {
            sum += d.getCost();
            dishNames += d.getName() + ", ";
        }
    sum = Delivery.deliveryFee(sum);
    if(Delivery.delivery){
        TextUI.sendMessage("You have ordered: "+ dishNames + "To this address: " + Main.getCurrentAccount().getAddress() +"\nThe total is: " + sum + "\nThank you for choosing us. " + "(Order number: " + randomUUID() +").");
    return;
    }
        TextUI.sendMessage("You have ordered: "+ dishNames + "please collect the food, it is ready in 15 minutes.\nThe total is: " + sum + "\nThank you for choosing us. " + "(Order number: " + randomUUID() +").");
    }
}
