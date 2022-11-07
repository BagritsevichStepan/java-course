package markup;

import java.util.List;

public class Strong extends AbstractElement implements Paragraphable {
    public Strong(List<Paragraphable> content) {
        super(content);
    }

    @Override
    protected String getMarkdownTag() {
        return "__";
    }

    @Override
    protected String getOpenHtmlTag() {
        return "<strong>";
    }

    @Override
    protected String getCloseHtmlTag() {
        return "</strong>";
    }
}
