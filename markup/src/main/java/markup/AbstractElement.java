package markup;

import java.util.List;

public abstract class AbstractElement implements Content {
    protected final List<? extends Content> content;

    AbstractElement(List<? extends Content> content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder markdown) {
        markdown.append(getMarkdownTag());
        for (Content x : content) {
            x.toMarkdown(markdown);
        }
        markdown.append(getMarkdownTag());
    }

    @Override
    public void toHtml(StringBuilder html) {
        html.append(getOpenHtmlTag());
        for (Content x : content) {
            x.toHtml(html);
        }
        html.append(getCloseHtmlTag());
    }

    protected abstract String getMarkdownTag();

    protected abstract String getOpenHtmlTag();

    protected abstract String getCloseHtmlTag();
}
