import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
public class Parser {
    // Alt kode fra TextFlix

/*


//comment

import java.io.StringReader;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


//TODO Victor - comments til Parser

    public class Parser {

        /// CSV
        // CSV -> Object
        public static ArrayList<Media> parseDataFromCsvMedia(ArrayList<String> data) {
            ArrayList<Media> media = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                String s = data.get(i);
                String[] values = s.split(";");
                String mediaName = values[0];
                int2 years = getYears(values[1]);
                int releaseYear = years.a;
                int endYear = years.b;
                ArrayList<Category> categories = getCategories(values[2]);

                double rating = Double.parseDouble(values[3].replace(",","."));
                Media m;

                if(values.length > 4){
                    ArrayList<Season> seasons = parseSeasonDataFromCsv(values[4]);
                    m = new Serie(mediaName, releaseYear, categories, rating, endYear, seasons);
                }
                else {
                    m = new Movie(mediaName, releaseYear, categories, rating);
                }
                media.add(m);
            }
            return media;
        }

        // String -> Arraylist<Category>
        public static ArrayList<Category> getCategories(String data) {
            String[] categoriesData = data.split(",");
            ArrayList<Category> categories = new ArrayList<>();
            for(String s : categoriesData) {
                categories.add(Category.findCategory(s));
            }
            return categories;
        }
        public static int2 getYears(String data){
            String[] years = data.split("-");
            int releaseYear = Integer.parseInt(years[0].replace(" ",""));
            int endYear = -1;
            if(years.length > 1){
                String temp = years[1].replace(" ","");
                if(!temp.isEmpty()) {
                    endYear = Integer.parseInt(temp);
                }
            }
            return new int2(releaseYear, endYear);
        }

        /// Json
        // Json -> Object
        public static ArrayList<Account> parseDataFromJsonAccount(String data){
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Account>>(){}.getType();

            ArrayList<Account> a = gson.fromJson(data, listType);
            if(a == null){
                return new ArrayList<>();
            }

            return a;
        }
        public static ArrayList<User> parseDataFromJsonUser(String data){
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();

            ArrayList<User> users = gson.fromJson(data, listType);
            if(users == null){
                return new ArrayList<>();
            }

            return users;
        }

        // Object -> Json
        public static String serializeMediaData(ArrayList<Media> data){
            return serializeData(data);
        }
        public static String serializeAccountData(ArrayList<Account> data){
            return serializeData(data);
        }
        public static String serializeData(Object obj){
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();

            Gson gson = builder.create();

            return gson.toJson(obj);
        }
        public static String serializeData(Object obj, boolean setPrettyPrinting){
            GsonBuilder builder = new GsonBuilder();
            if(setPrettyPrinting) {
                builder.setPrettyPrinting();
            }
            Gson gson = builder.create();

            return gson.toJson(obj);
        }

        /// Misc parser
        public static ArrayList<Season> parseSeasonDataFromCsv(String data) {
            ArrayList<Season> returnData = new ArrayList<>();
            String[] values = data.split(",");
            for(int i = 0; i < values.length; i++){
                String[] seasonAndEpisode = values[i].split("-");
                returnData.add(new Season(Integer.parseInt(seasonAndEpisode[0].replace(" ","")), Integer.parseInt(seasonAndEpisode[1].replace(" ",""))));
            }
            return returnData;
        }
        public static ArrayList<Media> parseSerieDataFromResultSet(ResultSet resultSet) {
            ArrayList<Media> results = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    String Name = resultSet.getString("Name");
                    String stringYears = resultSet.getString("Years");
                    String stringCategory = resultSet.getString("Category");
                    double Rating = resultSet.getDouble("Rating");
                    String stringSeasons = resultSet.getString("Seasons");
                    ArrayList<Category> categories = Parser.getCategories(stringCategory);
                    ArrayList<Season> seasons = Parser.parseSeasonDataFromCsv(stringSeasons);

                    int2 years = Parser.getYears(stringYears);
                    int releaseYear = years.a;
                    int endYear = years.b;
                    //Arrayliste af vores media
                    Media m = new Serie(Name, releaseYear, categories, Rating, endYear, seasons);
                    results.add(m);
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
            return results;
        }

        public static ArrayList<Account> parseAccountDataFromResultSet(ResultSet resultSet) {
            ArrayList<Account> results = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    String firstname = resultSet.getString("firstName");
                    String lastname = resultSet.getString("lastName");
                    String username = resultSet.getString("userName");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    String jsonUser = resultSet.getString("users");
                    int sqlID = resultSet.getInt("AccountID");
                    ArrayList<User> users = parseDataFromJsonUser(jsonUser);
                    //Arrayliste af vores media
                    Account a = new Account(firstname, lastname, username, password, email, users, sqlID);
                    results.add(a);
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
            return results;
        }
    }


*/
}