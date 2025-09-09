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
            throw new RuntimeException("Esperado " + type + " mas encontrado " + currentToken);
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
    }

    private void parseArray(){
        eat(TokenType.LBRACKET);

        if (currentToken.type != TokenType.RBRACKET){
            parseValue();
            while(currentToken.type == TokenType.COMMA) {
                eat(TokenType.COMMA);
                parseValue();
            }
        }
        eat(TokenType.RBRACKET);
    }

    private void parsePair() {
        eat(TokenType.STRING);
        eat(TokenType.COLON);
        parseValue();
    }

    private void parseValue() {
        switch (currentToken.type) {
            case STRING -> eat(TokenType.STRING);
            case NUMBER -> eat(TokenType.NUMBER);
            case TRUE -> eat(TokenType.TRUE);
            case FALSE -> eat(TokenType.FALSE);
            case NULL -> eat(TokenType.NULL);
            case LBRACE -> parseObject();
            case LBRACKET -> parseArray();
            default -> throw new RuntimeException("Unrecognized token: " + currentToken);
        }
    }
}
