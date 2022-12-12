public class Delivery {
    // Vi laver nogen forskellige "byer" man "kun" kan bestille fra, og så kan man altid udvide efterfølgende.
    // Ballerup, Snekkersten, Værløse & Aalborg
    // Den skal kunne bestemme en ændring i den totale pris, alt afhængig om man vil have levering
    public static int deliveryFee(int cost) {
        String checkForDelivery = TextUI.getUserInput("Do you want delivery? The fee is 50,- \nYes or no?");
        int totalCost = cost;
        switch (checkForDelivery.toLowerCase()) {
            case "yes":
                totalCost += 50;
                TextUI.sendMessage("Delivery fee has been added to your order, the total is: " + totalCost);
                return totalCost;
            case "no":
                TextUI.sendMessage("You will get notified when your order is ready for pickup.");
                return totalCost;

            default:
                TextUI.sendMessage("Invalid input, try again.");
                deliveryFee(cost);
        }
    return 0;
    }

}
