package markup;

import java.util.List;

public class Strikeout extends AbstractElement implements Paragraphable {
    public Strikeout(List<Paragraphable> content) {
        super(content, ContentType.STRIKEOUT);
    }
}
