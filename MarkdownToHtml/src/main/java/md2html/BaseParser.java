package md2html;

import java.io.IOException;

public abstract class BaseParser {
    private CharSource source;
    private BackoffSource lostSource = new BackoffSource("");
    protected char prevChar;
    protected char curChar = EOF;
    protected static final char EOF = 0;

    protected BaseParser(CharSource source) throws IOException {
        this.source = source;
        nextChar();
    }

    protected void nextChar() throws IOException {
        prevChar = curChar;
        if (lostSource.hasNext()) {
            curChar = lostSource.getNext();
        } else {
            if (!lostSource.sourceIsEmpty()) {
                lostSource = new BackoffSource("");
            }
            curChar = (source.hasNext()) ? source.getNext() : EOF;
        }
    }

    protected void skipEmptyLines() throws IOException {
        while (!fileEnded() && isLineSeparator(curChar)) {
            nextChar();
        }
    }

    protected boolean fileEnded() {
        return curChar == EOF;
    }

    protected boolean isLineSeparator(char c) {
        return !String.valueOf(c).matches(".");
    }

    protected boolean test(char expected) throws IOException {
        if (curChar == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected boolean testWhitespace() throws IOException {
        if (Character.isWhitespace(curChar)) {
            nextChar();
            return true;
        }
        return false;
    }

    protected boolean test(String expected) throws IOException {
        int equalPrefLength = 0;
        for (char c : expected.toCharArray()) {
            if (!test(c)) {
                returnSourcePart(expected.substring(0, equalPrefLength));
                return false;
            }
            equalPrefLength++;
        }
        return true;
    }

    protected void returnSourcePart(String part) throws IOException {
        if (part.equals("")) {
            return;
        }

        part += curChar;
        if (lostSource.sourceIsEmpty()) {
            lostSource = new BackoffSource(part);
        } else {
            lostSource.returnSourceSuf(part.length());
        }
        nextChar();
    }
}
