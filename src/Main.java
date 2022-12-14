import java.io.File;
import java.util.ArrayList;

public class Main {
    private static UIMenu currentMenu;

    private static Account currentAccount;
    private static City currentCity;
    private static IO io;

    //Test
    public static void main(String[] args) {
        io = new DatabaseIO();
        io.setup();
       new StartMenu();
       shutDown();

    }
    public static void shutDown(){
        io.saveAccountData();
        System.exit(0);
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account currentAccount) {
        Main.currentAccount = currentAccount;
    }

    public static void setCurrentMenu(UIMenu currentMenu) {
        Main.currentMenu = currentMenu;
    }

    public static UIMenu getCurrentMenu() {
        return currentMenu;
    }

    public static City getCurrentCity() {
        return currentCity;
    }

    public static void setCurrentCity(City currentCity) {
        Main.currentCity = currentCity;
    }

    public static IO getIo() {
        return io;
    }

    public static void setIo(IO io) {
        Main.io = io;
    }
}