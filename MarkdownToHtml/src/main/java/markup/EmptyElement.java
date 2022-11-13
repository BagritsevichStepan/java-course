package markup;

import java.util.List;

public class EmptyElement extends AbstractElement implements Paragraphable {
    public EmptyElement(List<Paragraphable> content) {
        super(content, ContentType.EMPTY_ELEMENT);
    }
}
