package expression.exceptions;

public class UndefinedUnaryOperatorException extends UndefinedOperatorException {
    public UndefinedUnaryOperatorException(String message, int errorPos) {
        super("unary", message, errorPos);
    }
}
