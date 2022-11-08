package markup;

import java.util.List;

public class ListItem extends AbstractElement implements Content {
    public ListItem(List<Listable> content) {
        super(content);
    }

    public String getOpenHtmlTag() {
        return "<li>";
    }

    public String getCloseHtmlTag() {
        return "</li>";
    }

    public String getMarkdownTag() {
        throw new UnsupportedOperationException("ListItem hasn't Markdown Tag");
    }
}
