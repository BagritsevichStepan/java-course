package expression.exceptions;

public class UndefinedOperatorException extends ParsingException {
    public UndefinedOperatorException(String message, int errorPos) {
        super("Undefined operator " + message, errorPos);
    }

    public UndefinedOperatorException(String operatorType, String message, int errorPos) {
        super("Undefined " + operatorType + " operator " + message, errorPos);
    }
}
