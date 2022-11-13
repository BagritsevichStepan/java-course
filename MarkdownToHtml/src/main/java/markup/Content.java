package markup;

public interface Content {
    void toMarkdown(StringBuilder markdown);

    void toHtml(StringBuilder html);
}
