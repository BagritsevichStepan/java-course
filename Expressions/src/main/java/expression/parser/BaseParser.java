package expression.parser;

public abstract class BaseParser implements Parser {
    protected CharSource source;
    protected char curChar;
    protected int curPos;
    protected static final char EOF = 0;

    protected void setSource(CharSource newSource) {
        source = newSource;
        nextChar();
    }

    protected abstract void nextChar();

    protected abstract int getCurPos();

    protected boolean test(char expected) {
        if (curChar == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected void skipWhitespaces() {
        while (isWhitespace(curChar)) {
            nextChar();
        }
    }

    protected static boolean isWhitespace(char c) {
        return Character.isWhitespace(c);
    }

    protected static boolean isDigit(char c) {
        return Character.isDigit(c);
    }
}
