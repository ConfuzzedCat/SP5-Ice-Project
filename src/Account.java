public class Account {
    public String userName;
    public String password;
    public String email;
    private int SQLID;

    public Account(String userName, String password, String email, int SQLID) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.SQLID = SQLID;
    }

    public static Account register() {
        String username = TextUI.getUserInput("Please type your username.");
        String password = TextUI.getUserInput("Please type your password.");
        Account newAccount = new Account(username, password);
        AddAccountToList(newAccount);
        return newAccount;

        public static Account register () {
            String username = TextUI.getUserInput("Please type your username.");
            String password = TextUI.getUserInput("Please type your password.");
            Account newAccount = new Account(username, password);
            AddAccountToList(newAccount);
            return newAccount;

        }
    }

        private boolean tryLogin (String username, String password){
            if (this.userName.equalsIgnoreCase(username)) {

                while (!this.password.equals(password)) {

                    TextUI.sendMessage("Password was incorrect, please try again!");
                    password = TextUI.getUserInput("Please type your password again.");
                }
                TextUI.sendMessage("\nCredentials is correct!");
                return true;

            }
            return false;
        }
    }
        public int getSQLID () {
            return SQLID;
        }
}