package Classes;

public class Lexer {
    private final String input;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public Token nextToken() {
        skipWhitespace();

        if (pos >= input.length()) {
            return new Token(TokenType.EOF, null);
        }

        char c = input.charAt(pos);

        return switch (c) {
            case '{' -> {
                pos++;
                yield new Token(TokenType.LBRACE, "{");
            }
            case '}' -> {
                pos++;
                yield new Token(TokenType.RBRACE, "}");
            }
            case ':' -> {
                pos++;
                yield new Token(TokenType.COLON, ":");
            }
            case ',' -> {
                pos++;
                yield new Token(TokenType.COMMA, ",");
            }
            case '"' -> stringToken();
            default -> throw new RuntimeException("Unexpected Character: " + c);
        };
    }

    private Token stringToken() {
        pos++; //pular o "
        StringBuilder sb = new StringBuilder();

        while (pos < input.length() && input.charAt(pos) != '"') {
            sb.append(input.charAt(pos));
            pos++;
        }
        if (pos >= input.length()) {
            throw new RuntimeException("Not Closed String");
        }
        pos++; // pula o último "

        return new Token(TokenType.STRING, sb.toString());
    }

    private void skipWhitespace() {
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }
    }
}
