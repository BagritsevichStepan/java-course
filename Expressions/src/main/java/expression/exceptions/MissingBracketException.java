package expression.exceptions;

public class MissingBracketException extends ParsingException {
    public MissingBracketException(String message, int errorPos) {
        super("Missing '" + message + "' bracket", errorPos);
    }
}
