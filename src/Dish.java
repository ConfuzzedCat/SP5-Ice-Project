public class Dish {
    // Denne klasse skal indeholde rettens navn og pris.

   private String name;
    private int cost;

    public Dish(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name; //TODO Gør pænt :-)
    }
}
