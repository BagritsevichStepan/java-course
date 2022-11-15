package md2html;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class FileSource implements CharSource {
    private final Reader reader;
    private char[] buffer = new char[1024];
    private int pos;
    private int read;

    private void read() throws IOException {
        if (pos == read) {
            read = reader.read(buffer);
            pos = 0;
        }
    }

    public FileSource(InputStream inputStream) throws IOException {
        reader = new InputStreamReader(inputStream);
    }

    public FileSource(InputStream inputStream, String charsetName) throws IOException {
        reader = new InputStreamReader(inputStream, charsetName);
    }

    @Override
    public boolean hasNext() throws IOException {
        read();
        return read != -1;
    }

    @Override
    public char getNext() throws IOException {
        read();
        return buffer[pos++];
    }

    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }
}
