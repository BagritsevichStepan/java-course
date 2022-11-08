package markup;

import java.util.List;

public class Code extends AbstractElement implements Paragraphable {
    public Code(List<Paragraphable> content) {
        super(content, ContentType.CODE);
    }
}
