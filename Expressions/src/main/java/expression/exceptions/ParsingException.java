package expression.exceptions;

public class ParsingException extends Exception {
    public ParsingException(String message, int errorPos) {
        super("Parsing error at " + errorPos + ", " + message);
    }
}
