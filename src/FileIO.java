import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

    //Rette til

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
    public void setup() {
        ArrayList<String> data = FileIO.readDataLines("Data/Movies.csv");
        ArrayList<String> dataSeries = FileIO.readDataLines("Data/Series.csv");
        data.addAll(dataSeries); // Adder alt fra dataSeries over i data, så begge ting bliver gemt under data.

        Catalogue.allMedia = Parser.parseDataFromCsvMedia(data);


    }
}
