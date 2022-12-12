import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {
    //Test

    static Scanner scan = new Scanner(System.in);

    public static void sendObjectAsMessage(Object obj){
        sendMessage(obj.toString());
    }

    //**** bruges til den visuelle del af koden når den køres
    public static void sendMessage(String msg) {
        System.out.println("*********************");
        System.out.println(msg);
    }

    public static void consumeLine(){
        scan.nextLine();
    }
    public static String getUserInput(String text){
        sendMessage(text);
        String input = scan.nextLine();
        consumeLine();
        return input;
    }

    public static int getUserInput(String msg, ArrayList<String> options){

        sendMessage(msg);

        for(int i = 0; i<options.size(); i++){

            sendMessage(i+1+". "+options.get(i));
        }
        int choice = scan.nextInt();
        consumeLine();
        return choice;

    }

}
