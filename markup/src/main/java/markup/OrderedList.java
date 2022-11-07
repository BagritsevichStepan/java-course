package markup;

import java.util.List;

public class OrderedList extends AbstractElement implements Listable {
    public OrderedList(List<ListItem> content) {
        super(content);
    }

    public String getOpenHtmlTag() {
        return "<ol>";
    }

    public String getCloseHtmlTag() {
        return "</ol>";
    }

    public String getMarkdownTag() {
        throw new UnsupportedOperationException("OrderedList hasn't Markdown Tag");
    }
}
