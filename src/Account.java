import java.util.ArrayList;

public class Account {
    private static ArrayList<Account> accounts = new ArrayList<>();
    public String userName;
    public String password;
    public String email;
    private int SQLID;

    public Account(String userName, String password, String email, int id) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.SQLID = id;
    }

    private Account(String userName, String password) {
        this.userName = userName;
        this.email = TextUI.getUserInput("Please enter your email. ");
        this.password = password;

        TextUI.sendMessage("Registration was successful!");
    }

    public static void AddAccountToList(Account a){
        accounts.add(a);
    }

    public static Account register(String username, String password) {
        Account newAccount = new Account(username, password);
        AddAccountToList(newAccount);
        return newAccount;
    }

    public static Account register() {
        String username = TextUI.getUserInput("Please type your username.");
        String password = TextUI.getUserInput("Please type your password.");
        Account newAccount = new Account(username, password);
        AddAccountToList(newAccount);
        return newAccount;
    }


    public static Account login(){
        String username = TextUI.getUserInput("Please type your username.");
        String password = TextUI.getUserInput("Please type your password.");
        if(Main.getIo().getClass() == DatabaseIO.class){
            return DatabaseIO.login(username, password);
        }
        if( accounts == null || accounts.size() == 0){
            TextUI.sendMessage("No accounts in database, will start registering you now.");
            return register(username, password);
        }
        for (Account a:accounts){
            if(a.tryLogin(username, password)){
                return a;
            }
        }
        TextUI.sendMessage("No account found. Will start register you now.");
        return register(username, password);
    }
    private boolean tryLogin(String username, String password){
        if(this.userName.equalsIgnoreCase(username)){

            while (!this.password.equals(password)){

                TextUI.sendMessage( "Password was incorrect, please try again!" );
                password = TextUI.getUserInput("Please type your password again.");
            }
            TextUI.sendMessage("\nCredentials is correct!");
            return true;

        }
        return false;

    }

        public int getSQLID () {
            return SQLID;
        }
}