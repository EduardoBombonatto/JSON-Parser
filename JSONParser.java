import java.io.File;

public class JSONParser {
    public static void main(String[] args) {
        if(args.length != 1){
            System.err.println("Usage: java JSONParser <filename>");
            System.exit(1);
        }
        String filename = args[0];

        File file = new File(filename);
        System.out.println(file.length());
        return;
    }
}
