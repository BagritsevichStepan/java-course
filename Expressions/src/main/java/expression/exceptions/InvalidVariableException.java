package expression.exceptions;

public class InvalidVariableException extends ParsingException {
    public InvalidVariableException(String message, int errorPos) {
        super("Invalid variable name " + message, errorPos);
    }
}
