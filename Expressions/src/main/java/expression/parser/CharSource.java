package expression.parser;

public interface CharSource {
    boolean hasNext();
    char getNext();
    int getPos();
}
