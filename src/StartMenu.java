public class StartMenu implements UIMenu {
    //Test
    public void showMenu(){

        TextUI.sendMessage("Welcome to Five Stars Only!");
        TextUI.sendMessage("Please Login, Register or use a guest login.");

        String choice = TextUI.getUserInput("If you want to Login type: Login\nIf you want to register type: Register\n If you want to use a guest login type: Guest\n If you want to close Five Stars Only type: Exit");

        switch(choice.toLowerCase()){
            case "login":
                Account a = Account.login();
                if(a == null){
                    TextUI.sendMessage("Too many incorrect tries...");
                    showMenu();
                }
                Main.setCurrentAccount(a);
                new MainUIMenu();

                break;

            case "register":
                Main.setCurrentAccount(Account.register());
                new MainUIMenu();

                break;
            case "exit":
                goBack();

            case "guest":
                Account g = new Account("Guest","","","");
                Main.setCurrentAccount(g);
                new MainUIMenu();
                break;

            default:
                TextUI.sendMessage("Invalid input.");
                break;

        }
    }
    public StartMenu() {

        this.showMenu();
    }


    public void goBack() {

        return;
    }

}
