package markup;

public class Text implements Paragraphable {
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    public void toMarkdown(StringBuilder markdown) {
        markdown.append(text);
    }

    public void toHtml(StringBuilder html) {
        html.append(text);
    }
}
