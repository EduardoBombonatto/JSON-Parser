import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSONParser {
    public static void main(String[] var0) {
        if (var0.length != 1) {
            System.err.println("Usage: java JSONParser <filename>");
            System.exit(1);
        }

        String fileName = var0[0];

        try {
            String file = Files.readString(Path.of(fileName)).trim();

            if (isValidJson(file)) {
                System.out.println("Valid Json");
                System.exit(0);
            } else {
                System.out.println("Invalid Json");
                System.exit(1);
            }

        } catch (IOException e) {
            System.err.println("Error reading file " + fileName);
            System.exit(1);
        }
    }

    private static boolean isValidJson(String json) {
        return json.equals("{}");
    }
}