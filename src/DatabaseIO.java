import java.util.ArrayList;
import java.sql.*;
import java.util.Arrays;

public class DatabaseIO implements IO{
    //TODO: implement Methoderne!
        private static String DBhostname;
        private static String DBusername = "root";
        private static String DBpassword;
        private static Connection DBconnection;

        public static Account login(String username, String password) {
            String query = "SELECT * FROM textflix.accounts WHERE userName = ?";
            try{

                ResultSet resultSet = sendQuery(query, username);
                if(resultSet.next()) {
                    if (resultSet.getString("password").equals(password)) {
                        ArrayList<Account> accounts = getAccountsFromDB(sendQuery(query, username));
                        TextUI.sendMessage(accounts.toString());
                        return accounts.get(0);
                    }
                    for (int i = 0; i < 3; i++) {
                        String input = TextUI.getUserInput("Password was incorrect. Try again: ");
                        if (password.equals(resultSet.getString("password"))) {
                            return getAccountsFromDB(resultSet).get(0);
                        }
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return null;
        }
//TODO FIX LOL
        public void setup(){
            DBhostname = "jdbc:mysql://localhost/textflix?" + "autoReconnect=true&useSSL=false";
            establishConnection();
            ArrayList<Media> movies = loadMovieData();
            ArrayList<Media> series = loadSerieData();
            movies.addAll(series);
            Catalogue.allMedia = movies;

            Account.setAccounts(loadAccountData());

        }

        @Override
        public void saveAccountData() {
            Account a = Account.getCurrentAccount();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(a.getAccountname(), a.getPassword(), a.getEmail(),a.getAddress()));
            String query = "INSERT INTO textflix.accounts (users, firstName, lastName, userName, password, email) VALUES (?,?,?,?,?,?);";
            //TODO Ændre SQL query

            if(a.getSQLID() > 1) {
                data.clear();
                query = "UPDATE textflix.accounts SET users=? WHERE AccountID=?";
                data.add(String.valueOf(a.getSQLID()));
            }
            try{
                int r = sendStatement(preparedQuery(query, data));
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        private void establishConnection() {
            // connection
            DBpassword = TextUI.getUserInput("Skriv dit MySQL server password.");
            try {
                DBconnection = DriverManager.getConnection(DBhostname, DBusername, DBpassword);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        private ArrayList<Account> loadAccountData(){
            ArrayList<Account> results = new ArrayList<>();
            // statement
            String query = "SELECT * FROM textflix.accounts;";
            try {
                ResultSet resultSet = sendQuery(query);
                return getAccountsFromDB(resultSet);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return results;
        }

        private MenuCard loadMenuCardData(String restaurantName){
            ArrayList<Dish> dishes = new ArrayList<Dish>();
            // statement
            String query = "SELECT restaurantid FROM fivestarsonly.restaurants WHERE restaurantname = " + restaurantName + ";";


            try {
                ResultSet resultSet = sendQuery(query);
                while(resultSet.next()) {

                    int id = resultSet.getInt("restaurantid");

                    //Arrayliste af vores media
                    String queryDishes = "SELECT * FROM fivestarsonly.dishes WHERE restaurantid = "+ id +";";

                    ResultSet resultSetDishes = sendQuery(queryDishes);
                    while(resultSetDishes.next()) {
                        Dish d = new Dish(resultSetDishes.getString("dishname"),resultSetDishes.getInt("dishcost"));
                        dishes.add(d);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            MenuCard results = new MenuCard(dishes,restaurantName);
            return results;
        }


        public static ResultSet sendQuery(String query) throws SQLException {
            try {
                PreparedStatement statement = DBconnection.prepareStatement(query);
                // resultset
                return statement.executeQuery();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            throw new SQLException("No results found. :(");
        }
        public static int sendStatement(PreparedStatement statement) throws SQLException {
            try {
                return statement.executeUpdate();
            } catch (SQLException e){
                throw e;
            }
        }

        public static ResultSet sendQuery(String query, String data) throws SQLException {
            try {
                PreparedStatement statement = DBconnection.prepareStatement(query);
                statement.setString(1, data);

                // resultset
                return statement.executeQuery();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            throw new SQLException("No results found. :(");
        }

        public static PreparedStatement preparedQuery(String query, ArrayList<String> data) throws SQLException{
            try{
                PreparedStatement statement = DBconnection.prepareStatement(query);
                for (int i = 1; i <= data.size(); i++){
                    statement.setString(i, data.get(i-1));
                }
                return statement;

            } catch (SQLException e){
                throw e;
            }
        }


    private static ArrayList<Account> getAccountsFromDB(ResultSet resultSet){
        ArrayList<Account> results = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String accountname = resultSet.getString("accountName");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                int sqlID = resultSet.getInt("AccountID");

                //Arrayliste af vores media
                Account a = new Account(accountname, password, email, address, sqlID);
                results.add(a);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void saveAccountData(ArrayList<Account> accounts) {

    }


    @Override
    public ArrayList<City> loadCities() {
        return null;
    }

    @Override
    public MenuCard loadMenuCard() {
        return null;
    }
    // hej victor 8-)
    // helo marcus B)
    // hjælp.
    //Test
}
