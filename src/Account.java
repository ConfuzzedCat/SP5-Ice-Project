import java.util.ArrayList;
//Test
public class Account {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Account currentAccount;

    private String accountname;
    private String password;
    private String email;
    private String address;
    private int SQLID;

    //private int SQLID; //TODO: Tilføje igen når vi laver Database
    public Account(String accountname, String password, String email, String address) {
        this.accountname = accountname;
        this.password = password;
        this.email = email;
        this.address = address;
        if (address.isEmpty()) { //Hvis addressen er tom på vores account, så spørger vi vores metode om at udfylde den.
            cusAddress();
        }
        //this.SQLID = id; TODO: Database
    }
    public Account(String accountname, String password, String email, String address, int ID) {
        this.accountname = accountname;
        this.password = password;
        this.email = email;
        this.address = address;
        if (address.isEmpty()) { //Hvis addressen er tom på vores account, så spørger vi vores metode om at udfylde den.
            cusAddress();
        }
        this.SQLID = ID;
    }

    private Account(String accountname, String password) {
        this.accountname = accountname;
        this.email = TextUI.getUserInput("Please enter your email. ");
        this.password = password;

        TextUI.sendMessage("Registration was successful!");
    }
    public static String getCSVString(Account a) {
        return a.getAccountname() + "," + a.getPassword() + "," + a.getEmail() + "," + a.getAddress();
    }

    public static void AddAccountToList(Account a){
        accounts.add(a);
    }

    public static Account register(String accountname, String password) {
        Account newAccount = new Account(accountname, password);
        AddAccountToList(newAccount);
        newAccount.cusAddress();
        return newAccount;
    }

    public static Account register() {
        String accountname = TextUI.getUserInput("Please type your name.");
        String password = TextUI.getUserInput("Please type your password.");
        Account newAccount = new Account(accountname, password);
        AddAccountToList(newAccount);
        return newAccount;
    }


    public static Account login(){
        String accountname = TextUI.getUserInput("Please type your name.");
        String password = TextUI.getUserInput("Please type your password.");
        /*
        if(Main.getIo().getClass() == DatabaseIO.class){
            return DatabaseIO.login(accountname, password);
        }
        */

        if( accounts == null || accounts.size() == 0){
            TextUI.sendMessage("No accounts in database, will start registering you now.");
            return register(accountname, password);
        }
        for (Account a:accounts){
            if(a.tryLogin(accountname, password)){
                return a;
            }
        }
        TextUI.sendMessage("No account found. Will start register you now.");
        return register(accountname, password);
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account currentAccount) {
        Account.currentAccount = currentAccount;
    }

    private boolean tryLogin(String accountname, String password){
        if(this.accountname.equalsIgnoreCase(accountname)){

            while (!this.password.equals(password)){

                TextUI.sendMessage( "Password was incorrect, please try again!" );
                password = TextUI.getUserInput("Please type your password again.");
            }
            TextUI.sendMessage("\nCredentials is correct!");
            return true;

        }
        return false;

    }

    public void cusAddress(){
        address = TextUI.getUserInput("Please write your address: ");

    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

/*
    public int getSQLID () {
            return SQLID;
        }

 */
}