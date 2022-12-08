/*
FileIO : + scanner.File()
FileIO : + public String[] getLinesFromFile(String path);
FileIO : + public String getTextFromFile(String path);
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Alt kode fra TextFLix

public class FileIO implements IO {

    private static ArrayList<String> readDataLines(String path) {

        File file = new File(path);

        ArrayList<String> data = new ArrayList<>();

        try {
            Scanner input = new Scanner(file);
            String header = input.nextLine(); // ignore header
            while (input.hasNextLine()) {
                data.add(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            data = null;
        }
        return data;
    }

    private static String readData(String path) {

        File file = new File(path);

        String data = "";

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                data = data.concat(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            data = null;
        }
        return data;
    }

    private static boolean writeToFile(String path, String content) {

        try {
            FileWriter writer = new FileWriter(path);
            writer.write(content);

            writer.close();

        } catch (IOException e) {
            TextUI.sendMessage(e.toString());
        }

        return true;
    }

    @Override
    public void setup() {
        ArrayList<String> data = FileIO.readDataLines("Data/Movies.csv");
        ArrayList<String> dataSeries = FileIO.readDataLines("Data/Series.csv");
        data.addAll(dataSeries); // Adder alt fra dataSeries over i data, så begge ting bliver gemt under data.

        Catalogue.allMedia = Parser.parseDataFromCsvMedia(data);


    }

    @Override
    public void saveAccountData() {
        writeToFile("Data/Accounts.json", Parser.serializeAccountData(Account.getAccounts()));
    }
}

