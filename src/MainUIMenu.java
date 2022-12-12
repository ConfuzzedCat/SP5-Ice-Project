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
        Account a = Account.getCurrentAccount();

        ArrayList<String> list = new ArrayList<>(Arrays.asList("Konto-indstillinger", "Restauranter", "Support", "Log ud"));

        boolean tryAgain = true;

        while (tryAgain) {
            int choice = TextUI.getUserInput("Vælg venligst: ", list);
            switch (choice) {
                case 1:
                    TextUI.sendMessage("Konto-indstillinger");
                    tryAgain = false;
                    break;

                case 2:
                    TextUI.sendMessage("Restauranter");
                    tryAgain = false;
                    break;
                case 3:
                    TextUI.sendMessage("Support");
                    new StartMenu(); // logout
                    tryAgain = false;
                    break;
                default:
                    TextUI.sendMessage("Log ud");
                    break;

            }
        }
    }
}