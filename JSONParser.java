import Classes.Lexer;
import Classes.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSONParser {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java JSONParser <filename>");
            System.exit(1);
        }

        String fileName = args[0];

        try {
            String content = new String(Files.readAllBytes(Path.of(fileName)));
            Lexer lexer = new Lexer(content);
            Parser parser = new Parser(lexer);
            parser.parseObject();

            System.out.println("JSON Valido: " + content);
            System.exit(0);
        } catch (IOException e) {
            System.err.println("Error reading file " + e.getMessage());
            System.exit(1);
        }
    }

}