package expression.exceptions;

public class UndefinedBinaryOperatorException extends UndefinedOperatorException {
    public UndefinedBinaryOperatorException(String message, int errorPos) {
        super("binary", message, errorPos);
    }
}
