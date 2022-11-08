package expression.parser;

public class BackoffSource extends StringSource {
    BackoffSource(String source) {
        super(source);
    }

    public void returnSourceSuf(int sufLength) {
        pos -= sufLength;
    }

    public boolean isEmpty() {
        return source.length() == 0;
    }
}
