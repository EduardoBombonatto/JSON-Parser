package Classes;

public class Parser {
    private final Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.nextToken();
    }

    private void eat(TokenType type) {
        if (currentToken.type == type) {
            currentToken = lexer.nextToken();
        } else {
            throw new RuntimeException("Expected: " + type + " but found: " + currentToken);
        }
    }

    public void parseObject() {
        eat(TokenType.LBRACE);

        if (currentToken.type == TokenType.STRING) {
            parsePair();
            while (currentToken.type == TokenType.COMMA) {
                eat(TokenType.COMMA);
                parsePair();
            }
        }
        eat(TokenType.RBRACE);
        if (currentToken.type != TokenType.EOF) {
            throw new RuntimeException("Extras tokens after the Right Brace, INVALID!");
        }
    }

    private void parsePair() {
        eat(TokenType.STRING);
        eat(TokenType.COLON);
        eat(TokenType.STRING);
    }
}
