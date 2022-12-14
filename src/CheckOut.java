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
        TextUI.sendMessage("Du har bestilt: "+ dishNames + "til denne adresse: " + Main.getCurrentAccount().getAddress() +"\nDin total pris er: " + sum + "\nTak for din bestilling." + "(Ordrenummer: " + randomUUID() +").");
    return;
    }
        TextUI.sendMessage("Du har bestilt: "+ dishNames + "til afhentning, maden er klar om et kvarter.\nDin total pris er: " + sum + "\nTak for din bestilling." + "(Ordrenummer: " + randomUUID() +").");
    }
}
