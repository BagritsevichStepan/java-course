package md2html;

public class BackoffSource extends StringSource {
    BackoffSource(String source) {
        super(source);
    }

    public void returnSourceSuf(int sufLength) {
        pos -= sufLength;
    }

    public boolean sourceIsEmpty() {
        return source.length() == 0;
    }
}
