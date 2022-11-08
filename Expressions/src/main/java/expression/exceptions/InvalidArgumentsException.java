package expression.exceptions;

public class InvalidArgumentsException extends ExpressionException {
    public InvalidArgumentsException(String message) {
        super("Invalid arguments in " + message + " operation");
    }
}
