package markup;

import java.util.List;

public class UnorderedList extends AbstractElement implements Listable {
    public UnorderedList(List<ListItem> content) {
        super(content);
    }

    public String getOpenHtmlTag() {
        return "<ul>";
    }

    public String getCloseHtmlTag() {
        return "</ul>";
    }

    public String getMarkdownTag() {
        throw new UnsupportedOperationException("UnorderedList hasn't Markdown Tag");
    }
}
