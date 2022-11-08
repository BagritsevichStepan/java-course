package markup;

import java.util.List;

public class ListItem extends AbstractElement implements Content {
    public ListItem(List<Listable> content) {
        super(content, ContentType.LIST_ITEM);
    }
}
