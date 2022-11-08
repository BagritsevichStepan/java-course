package markup;

import java.util.List;

public class Delete extends AbstractElement implements Paragraphable {
    public Delete(List<Paragraphable> content) {
        super(content, ContentType.DELETE);
    }
}
