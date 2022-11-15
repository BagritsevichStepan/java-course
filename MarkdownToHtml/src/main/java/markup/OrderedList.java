package markup;

import java.util.List;

public class OrderedList extends AbstractElement implements Listable {
    public OrderedList(List<ListItem> content) {
        super(content, ContentType.ORDERED_LIST);
    }
}
