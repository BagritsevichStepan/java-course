package markup;

import java.util.List;

public class Emphasis extends AbstractElement implements Paragraphable {
    public Emphasis(List<Paragraphable> content) {
        super(content);
    }

    @Override
    protected String getMarkdownTag() {
        return "*";
    }

    @Override
    protected String getOpenHtmlTag() {
        return "<em>";
    }

    @Override
    protected String getCloseHtmlTag() {
        return "</em>";
    }
}
