package md2html;

public class StringSource implements CharSource {
    protected final String source;
    protected int pos;

    StringSource(String source) {
        this.source = source;
    }

    @Override
    public boolean hasNext() {
        return pos < source.length();
    }

    @Override
    public char getNext() {
        return source.charAt(pos++);
    }
}
