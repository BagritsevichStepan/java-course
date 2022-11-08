package markup;

import java.util.List;

public class Strikeout extends AbstractElement implements Paragraphable {
    public Strikeout(List<Paragraphable> content) {
        super(content);
    }

    @Override
    protected String getMarkdownTag() {
        return "~";
    }

    @Override
    protected String getOpenHtmlTag() {
        return "<s>";
    }

    @Override
    protected String getCloseHtmlTag() {
        return "</s>";
    }
}
