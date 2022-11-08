package markup;

import java.util.List;

public abstract class AbstractElement implements Content {
    protected final List<? extends Content> content;
    private final ContentType elementType;

    AbstractElement(List<? extends Content> content, ContentType elementType) {
        this.content = content;
        this.elementType = elementType;
    }

    @Override
    public void toMarkdown(StringBuilder markdown) {
        markdown.append(getOpenMarkdownTag());
        for (Content x : content) {
            x.toMarkdown(markdown);
        }
        markdown.append(getCloseMarkdownTag());
    }

    @Override
    public void toHtml(StringBuilder html) {
        html.append(getOpenHtmlTag());
        for (Content x : content) {
            x.toHtml(html);
        }
        html.append(getCloseHtmlTag());
    }

    protected String getOpenMarkdownTag() {
        return ContentType.getOpenMarkdownTag(elementType);
    }

    protected String getCloseMarkdownTag() {
        return ContentType.getCloseMarkdownTag(elementType);
    }

    protected String getOpenHtmlTag() {
        return ContentType.getOpenHtmlTag(elementType);
    }

    protected String getCloseHtmlTag() {
        return ContentType.getCloseHtmlTag(elementType);
    }
}
