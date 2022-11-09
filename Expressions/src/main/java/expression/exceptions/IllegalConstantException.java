package expression.exceptions;

public class IllegalConstantException extends ParsingException {
    public IllegalConstantException(String message, int errorPos) {
        super("Illegal constant " + message, errorPos);
    }
}
