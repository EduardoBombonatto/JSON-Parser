import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JSONParser {
    public static void main(String[] args) {
        if(args.length != 1){
            System.err.println("Usage: java JSONParser <filename>");
            System.exit(1);
        }
        String filename = args[0];

        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()){
                String data = reader.nextLine();
                if(data.equals("{}")) System.out.println(0);
                else System.out.println(1);
            }
        } catch (FileNotFoundException e){
            System.err.println("File not found: " + filename);
            System.exit(1);
        }
    }
}
