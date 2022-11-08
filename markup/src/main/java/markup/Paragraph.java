package markup;

import java.util.List;

public class Paragraph extends AbstractElement implements Listable {
    public Paragraph(List<Paragraphable> content) {
        super(content);
    }

    @Override
    protected String getMarkdownTag() {
        return "";
    }

    @Override
    protected String getOpenHtmlTag() {
        return "";
    }

    @Override
    protected String getCloseHtmlTag() {
        return "";
    }
}
