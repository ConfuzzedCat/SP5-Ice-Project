import java.sql.*;
import java.util.ArrayList;

    public class Searcher {
        public boolean checkForDatabase(IO ioMethod) {
            return ioMethod.getClass() == DatabaseIO.class;
        }

        public ArrayList<Dish> searchMedia(String prompt) {
            ArrayList<Dish> result = new ArrayList<>();

            if (checkForDatabase(Main.getIo())) {

                //: spørg om det skal være film, serie eller begge.
                String data = "%" + prompt + "%";
                try {
                    ResultSet resultSet = DatabaseIO.sendQuery("SELECT * FROM textflix.series WHERE NAME LIKE ?", data);
                    //TODO fix statement

                    return Parser.parseSerieDataFromResultSet(resultSet);
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
