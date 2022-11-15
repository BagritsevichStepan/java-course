package markup;

import java.util.List;

public class UnorderedList extends AbstractElement implements Listable {
    public UnorderedList(List<ListItem> content) {
        super(content, ContentType.UNORDERED_LIST);
    }
}
