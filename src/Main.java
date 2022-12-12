public class Main {
    private static StartMenu currentMenu;

    //Test
    public static void main(String[] args) {
    }
    private static Account currentAccount;
        public static void setCurrentAccount(Account currentAccount) {
            Main.currentAccount = currentAccount;
    }

    public static void setCurrentMenu(StartMenu currentMenu) {
        Main.currentMenu = currentMenu;
    }

    public static StartMenu getCurrentMenu() {
        return currentMenu;
    }
}