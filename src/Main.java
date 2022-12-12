import java.io.File;

public class Main {
    private static UIMenu currentMenu;

    //Test
    public static void main(String[] args) {

        System.out.println(new FileIO().returnRestaurantsOfCity(City.findCity("Værløse")));
        new Delivery().deliveryFee(100000);
        new Payment().creditCardInfo();
        new StartMenu();
    }
    private static Account currentAccount;
        public static void setCurrentAccount(Account currentAccount) {
            Main.currentAccount = currentAccount;
    }

    public static void setCurrentMenu(UIMenu currentMenu) {
        Main.currentMenu = currentMenu;
    }

    public static UIMenu getCurrentMenu() {
        return currentMenu;
    }
}