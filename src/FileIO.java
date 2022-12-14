import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileIO {

    //Rette til
    //Test

    private static ArrayList<String> readDataLines(String path) {

        File file = new File(path);

        ArrayList<String> data = new ArrayList<>();

        try {
            Scanner input = new Scanner(file);
            String header;
            if (path.endsWith(".csv")) {
                header = input.nextLine(); // ignore header
            }

            while (input.hasNextLine()) {
                data.add(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            data = null;
        }
        return data;
    }

    //Tilføje paths til pizza-place menukort (Rasmus har lavet)
    //hvor skal data bruges?
    public void setup() {

        for (Account a : loadAccountData()) {
            Account.AddAccountToList(a);
        }


/*
        Ligger et andet sted
        ArrayList<String> data = FileIO.readDataLines("Pizzaria Ballerup.txt");
        ArrayList<String> data2 = FileIO.readDataLines("Pizzaria Aalborg.txt");
        ArrayList<String> data3 = FileIO.readDataLines("Pizzaria Snekkersten.txt");
        ArrayList<String> data4 = FileIO.readDataLines("Pizzaria Værløse.txt");
        // Adder alt fra dataSeries over i data, så begge ting bliver gemt under data.
        data.addAll(data2);
        data.addAll(data3);
        data.addAll(data4);
        Catalogue.allMedia = Parser.parseDataFromCsvMedia(data);

*/

    }

    public ArrayList<String> returnRestaurantsOfCity(City c) {
        String path = "Data/Cities/" + c;
        ArrayList<String> results = new ArrayList<String>();

        File[] files = new File(path).listFiles();
//If this pathname does not denote a directory, then listFiles() returns null.

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName().replace(".txt", "")); //Fjerner .txt og tilføjer intet.
            }
        }

        return results;
    }

    //@Override
    public void saveAccountData(ArrayList<Account> accounts) {
        if(Main.getCurrentAccount().getAccountname().equalsIgnoreCase("Guest")){
            return;
        }
        try {
            // Opret en FileWriter, der skriver til den angivne fil
            FileWriter writer = new FileWriter("Data/Accounts.csv");
            // Skriv en overskriftslinje til filen
            writer.write("accountname, password, email, address\n");

            // For hvert Account-objekt i accounts-ArrayList'en, kald getCSVString-metoden
            // for at få en CSV-streng med oplysningerne fra Account-objektet. Skriv
            // CSV-strengen til filen.
            for (Account a : accounts) {
                writer.write(Account.getCSVString(a));
            }
            // Luk FileWriter-objektet
            writer.close();
        } catch (IOException e) {
            // Hvis der opstår en fejl, print stack trace
            e.printStackTrace();
        }
    }

    //@Override
    public ArrayList<Account> loadAccountData() {
        // Opret et File-objekt, der peger på den angivne fil
        File file = new File("Data/Accounts.csv");

        // Opret en tom ArrayList af Account-objekter kaldet accounts
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            // Opret en Scanner, der læser fra filen
            Scanner input = new Scanner(file);

            // Så længe der er en næste linje i filen, læs linjen og opret et Account-objekt
            // med de oplysninger, der findes på linjen. Tilføj Account-objektet til
            // accounts-ArrayList'en.
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] lineSplit = line.split(",");

                Account a = new Account(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3]);
                accounts.add(a);
            }
        } catch (FileNotFoundException e) {
            accounts = null;
            e.printStackTrace();
        }
        // Returner accounts-ArrayList'en
        return accounts;
    }

    //@Override
    public ArrayList<City> loadCities() {
        // Opret et File-objekt, der peger på filen på den angivne sti
        File file = new File("Data/Cities");

        // Brug list-metoden fra File-klassen til at få et array af strenge, hvor hver streng
        // er navnet på en undermappe i /Data/Cities-mappen. Dette array af strenge gemmes i
        // directories-variablen.
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {

                return new File(current, name).isDirectory();
            }
        });


        // Opret en tom ArrayList af City-objekter kaldet cities
        ArrayList<City> cities = new ArrayList<>();

        // Iterér over arrayet af mappenavne (directories) og brug
        // findCity-metoden fra City-klassen til at oprette et City-objekt for hvert
        // mappenavn. Tilføj disse City-objekter til cities-ArrayList'en.
        for (String s : directories) {
            cities.add(City.findCity(s));
        }

        // Returner cities-ArrayList'en
        return cities;
    }


    //@Override
    public MenuCard loadMenuCardFromRestaurant(String restaurant, City c) {
        // Opretter filen med data for restauranten i den angivne by
        File file = new File("Data/Cities/"+ c + restaurant +".csv");
        // Opretter en ArrayList til at indeholde retter fra filen
        ArrayList<Dish> dishesArr = new ArrayList<>();
        try {
            // Opretter en Scanner-instans til at læse filen
            Scanner input = new Scanner(file);
            // Læser filen linje for linje
            while (input.hasNextLine()) {
                String line = input.nextLine();
                // Deler hver linje i to dele adskilt af kommaet
                String[] lineSplit = line.split(",");

                // Konverterer prisen for retten til et heltal
                int dishesInt = Integer.parseInt(lineSplit[1]);
                // Opretter et Dish-objekt med navnet på retten og prisen
                Dish d = new Dish(lineSplit[0], dishesInt);
                // Tilføjer Dish-objektet til ArrayList'en
                dishesArr.add(d);
            }
        } catch (FileNotFoundException e) {
            // Hvis filen ikke findes, sættes dishesArr til null og exceptionen printes
            dishesArr = null;
            e.printStackTrace();
        }
        // Returnerer et nyt MenuCard-objekt oprettet med dishesArr og restaurant
        return new MenuCard(dishesArr,restaurant);
    }
}

