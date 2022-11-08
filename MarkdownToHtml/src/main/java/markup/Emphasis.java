package markup;

import java.util.List;

public class Emphasis extends AbstractElement implements Paragraphable {
    public Emphasis(List<Paragraphable> content) {
        super(content, ContentType.EMPHASIS);
    }
}
