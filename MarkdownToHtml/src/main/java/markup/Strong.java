package markup;

import java.util.List;

public class Strong extends AbstractElement implements Paragraphable {
    public Strong(List<Paragraphable> content) {
        super(content, ContentType.STRONG);
    }
}
