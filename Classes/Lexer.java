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
            default -> {
                if (Character.isDigit(c) || c == '-') {
                    yield numberToken();
                } else if (Character.isLetter(c)) {
                    yield keywordToken();
                } else {
                    throw new RuntimeException("Unrecognized character: " + c);
                }
            }
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

    private Token numberToken() {
        StringBuilder sb = new StringBuilder();

        if (input.charAt(pos) == '-') {
            sb.append('-');
            pos++;
        }
        while (pos < input.length() && (Character.isDigit(input.charAt(pos)))) {
            sb.append(input.charAt(pos));
            pos++;
        }
        if (pos < input.length() && input.charAt(pos) == '.') {
            sb.append('.');
            pos++;
            while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                sb.append(input.charAt(pos));
                pos++;
            }
        }
        return new Token(TokenType.NUMBER, sb.toString());
    }

    private Token keywordToken() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && Character.isLetter(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            pos++;
        }
        String word = sb.toString();
        return switch (word) {
            case "true" -> new Token(TokenType.TRUE, "true");
            case "false" -> new Token(TokenType.FALSE, "false");
            case "null" -> new Token(TokenType.NULL, "null");
            default -> throw new RuntimeException("Unrecognized word: " + word);
        };
    }

    private void skipWhitespace() {
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }
    }
}
