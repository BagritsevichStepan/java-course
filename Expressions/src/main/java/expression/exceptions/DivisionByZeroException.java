package expression.exceptions;

public class DivisionByZeroException extends ExpressionException {
    public DivisionByZeroException(String message) {
        super("Division by zero in " + message + " operation");
    }
}
