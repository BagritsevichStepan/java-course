package markup;

import java.util.List;

public class Insert extends AbstractElement implements Paragraphable {
    public Insert(List<Paragraphable> content) {
        super(content, ContentType.INSERT);
    }
}
