import java.sql.*;
import java.util.ArrayList;

    public class Searcher {
        public boolean checkForDatabase(IO ioMethod){
            if(ioMethod.getClass() == DatabaseIO.class){
                return true;
            }
            return false;
        }
        public ArrayList<Dish> searchMedia(String prompt){
            ArrayList<Dish> result = new ArrayList<>();

        if(checkForDatabase(io)){

            //: spørg om det skal være film, serie eller begge.
            String data = "%"+ prompt + "%";
            try {
                ResultSet resultSet = DatabaseIO.sendQuery("SELECT * FROM textflix.series WHERE NAME LIKE ?",  data);
                return Parser.parseSerieDataFromResultSet(resultSet);
            } catch (SQLException e){
                e.printStackTrace();
            }


            //bruge fileIO
            //parse prompt
            prompt = "(?i).*" + prompt +".*";
            for (Dish m : new FileIO().returnRestaurantsOfCity(Main.getCurrentCity())) {
                if(m.getName().matches(prompt)){
                    result.add(m);
                }
            }
            return result;
        }
        public ArrayList<Dish> searchMedia(Category c) {

            ArrayList<Dish> returnMedia = new ArrayList<>();
            for(Dish m : Catalogue.allMedia){
                for (Category category: m.categories) {
                    if(category == c){

                        returnMedia.add(m);
                    }
                }
            }

            return returnMedia;

        }
    }
}
