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

    //Tilføje paths til pizza-place menukort (Rasmus har lavet)
    //hvor skal data bruges?
    public void setup() {
        ArrayList<String> data = FileIO.readDataLines("Pizzaria Ballerup.txt");
        ArrayList<String> data2 = FileIO.readDataLines("Pizzaria Aalborg.txt");
        ArrayList<String> data3 = FileIO.readDataLines("Pizzaria Snekkersten.txt");
        data.addAll(data); // Adder alt fra dataSeries over i data, så begge ting bliver gemt under data.
        data.addAll(data2);
        data.addAll(data3);
        Catalogue.allMedia = Parser.parseDataFromCsvMedia(data);


    }
}
