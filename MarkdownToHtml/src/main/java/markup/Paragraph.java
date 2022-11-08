package markup;

import java.util.List;

public class Paragraph extends AbstractElement implements Paragraphable, Listable {
    public Paragraph(List<Paragraphable> content) {
        super(content, ContentType.PARAGRAPH);
    }
}
