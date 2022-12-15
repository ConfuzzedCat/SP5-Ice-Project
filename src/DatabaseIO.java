import java.util.ArrayList;
import java.sql.*;
import java.util.Arrays;

public class DatabaseIO implements IO{
        private static String DBhostname;
        private static String DBusername = "root";
        private static String DBpassword;
        private static Connection DBconnection;


        public static Account login(String accountname, String password) {
            String query = "SELECT * FROM fivestarsonly.accounts WHERE accountname = ?";
            try{

                ResultSet resultSet = sendQuery(query, accountname);
                if(resultSet.next()) {
                    if (resultSet.getString("password").equals(password)) {
                        ArrayList<Account> accounts = getAccountsFromDB(sendQuery(query, accountname));
                        return accounts.get(0);
                    }
                    for (int i = 0; i < 3; i++) {
                        password = TextUI.getUserInput("Password was incorrect. Try again: ");
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
        public void setup(){
            DBhostname = "jdbc:mysql://localhost/fivestarsonly?" + "autoReconnect=true&useSSL=false";
            establishConnection();

            Account.setAccounts(loadAccountData());

        }

        @Override
        public void saveAccountData() {
            Account a = Main.getCurrentAccount();
            if(a.getAccountname().equalsIgnoreCase("Guest")){
                return;
            }
            ArrayList<String> data = new ArrayList<>(Arrays.asList(a.getAccountname(), a.getPassword(), a.getEmail(),a.getAddress()));
            String query = "INSERT INTO fivestarsonly.accounts (accountname, password, email, address) VALUES (?,?,?,?);";

            try{
                int r = sendStatement(preparedQuery(query, data));
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }

        private void establishConnection() {
            // connection
            DBpassword = TextUI.getUserInput("Write your MySQL server password.");
            try {
                DBconnection = DriverManager.getConnection(DBhostname, DBusername, DBpassword);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public ArrayList<Account> loadAccountData(){
            ArrayList<Account> results = new ArrayList<>();
            // statement
            String query = "SELECT * FROM fivestarsonly.accounts;";
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
            String query = "SELECT restaurantid FROM fivestarsonly.restaurants WHERE restaurantname = \"" + restaurantName + "\";";


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
                int sqlID = resultSet.getInt("accountid");

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
    public ArrayList<City> loadCities() {
            String query = "SELECT * FROM fivestarsonly.cities;";
            ArrayList<City> resultCites = new ArrayList<>();
        try {
            ResultSet resultSet = sendQuery(query);
            while (resultSet.next()) {

                String cityname = resultSet.getString("cityname");
                resultCites.add(City.findCity(cityname));
            }
        }
            catch(SQLException e){
         e.printStackTrace();
            }

        return resultCites;
    }

    @Override
    public MenuCard loadMenuCard(String r) {
        return loadMenuCardData(r);
    }

    @Override
    public ArrayList<String> returnRestaurantsOfCity(City c) {
        ArrayList<String> restaurants = new ArrayList<String>();
        // statement
        String query = "SELECT cityid FROM fivestarsonly.cities WHERE cityname = \"" + c + "\";";
        try {
            ResultSet resultSet = sendQuery(query);
            while(resultSet.next()) {

                int id = resultSet.getInt("cityid");

                //Arrayliste af vores media
                String queryRestaurants = "SELECT * FROM fivestarsonly.restaurants WHERE cityid = "+ id +";";

                ResultSet resultSetRestaurants = sendQuery(queryRestaurants);
                while(resultSetRestaurants.next()) {
                    restaurants.add(resultSetRestaurants.getString("restaurantname"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }


    public static ArrayList<Dish> parseDishesFromResultSet(ResultSet resultset){

        ArrayList<Dish> dishes = new ArrayList<>();
        try{
            ResultSet resultSetDishes = resultset;
            while(resultSetDishes.next()) {
                Dish d = new Dish(resultSetDishes.getString("dishname"),resultSetDishes.getInt("dishcost"));
                dishes.add(d);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return dishes;
    }
}
