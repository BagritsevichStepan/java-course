package markup;

import java.util.List;

public class Header extends AbstractElement implements Paragraphable {
    private final int headerLevel;
    public Header(List<Paragraphable> content, int headerLevel) {
        super(content, ContentType.HEADER);
        this.headerLevel = headerLevel;
    }

    @Override
    protected String getOpenMarkdownTag() {
        return addHeaderLevelInMarkdown(ContentType.getOpenMarkdownTag(ContentType.HEADER));
    }

    @Override
    protected String getCloseMarkdownTag() {
        return addHeaderLevelInMarkdown(ContentType.getCloseMarkdownTag(ContentType.HEADER));
    }

    private String addHeaderLevelInMarkdown(String markdownTag) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < headerLevel; i++) {
            sb.append(markdownTag);
        }
        return sb.toString();
    }

    @Override
    protected String getOpenHtmlTag() {
        return addHeaderLevelInHtml(ContentType.getOpenHtmlTag(ContentType.HEADER));
    }

    @Override
    protected String getCloseHtmlTag() {
        return addHeaderLevelInHtml(ContentType.getCloseHtmlTag(ContentType.HEADER));
    }

    private String addHeaderLevelInHtml(String s) {
        String htmlTag = ContentType.CONTENT_TYPE_TO_HTML_TAG.get(ContentType.HEADER);
        int splitIndex = s.indexOf(htmlTag) + htmlTag.length();
        return s.substring(0, splitIndex) + headerLevel + s.substring(splitIndex);
    }
}
