public class StartMenu {
    public void showMenu(){

        TextUI.sendMessage("Welcome to TextFlix!");
        TextUI.sendMessage("Please Login or Register.");

        String choice = TextUI.getUserInput("If you want to Login type: Login\nIf you want to register type: Register\nIf you want to close TextFlix type: Exit");

        switch(choice.toLowerCase()){
            case "login":
                Account a = Account.login();
                if(a == null){
                    TextUI.sendMessage("Too many incorrect tries...");
                    showMenu();
                }
                Main.setCurrentAccount(a);
                new MainMenu();

                break;

            case "register":
                Main.setCurrentAccount(Account.register());
                new MainMenu();

                break;
            case "exit":
                goBack();

            default:
                TextUI.sendMessage("Invalid input.");
                break;

        }
    }
    public StartMenu() {
        this.showMenu();
    }

    @Override
    public void goBack() {
        return;
    }

}
