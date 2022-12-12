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

    sum = Delivery.deliveryFee(sum);
    TextUI.sendMessage("Du har bestilt: "+ dishNames + ".\nDin total pris er: " + sum + "\nTak for din bestilling." + "(Ordrenummer: " + randomUUID() +").");

        }
    }

}
