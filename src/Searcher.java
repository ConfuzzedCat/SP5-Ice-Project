import java.sql.*;
import java.util.ArrayList;

    public class Searcher {
        public boolean checkForDatabase(IO ioMethod) {
            return ioMethod.getClass() == DatabaseIO.class;
        }

        public ArrayList<Dish> searchDishes(String prompt) {
            ArrayList<Dish> result = new ArrayList<>();

            if (checkForDatabase(Main.getIo())) {

                //: spørg om det skal være film, serie eller begge.
                String data = "%" + prompt + "%";
                try {
                    ResultSet resultSet = DatabaseIO.sendQuery("SELECT * FROM fivestarsonly.dishes WHERE NAME LIKE ?", data);

                    return DatabaseIO.parseDishesFromResultSet(resultSet);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            //bruge fileIO
            //parse prompt
            prompt = "(?i).*" + prompt + ".*";
            for (String restaurant : new FileIO().returnRestaurantsOfCity(Main.getCurrentCity())) {
                MenuCard menu = new FileIO().loadMenuCardFromRestaurant(restaurant, Main.getCurrentCity());
                for (Dish d : menu.getDishes()) {
                    if (d.getName().matches(prompt)) {
                        result.add(d);
                    }
                }

            }
            return result;
        }

    }
