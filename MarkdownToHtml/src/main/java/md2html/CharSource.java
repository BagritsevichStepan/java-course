package md2html;

import java.io.IOException;

public interface CharSource {
    boolean hasNext() throws IOException;
    char getNext() throws IOException;
    void close() throws IOException;
}
