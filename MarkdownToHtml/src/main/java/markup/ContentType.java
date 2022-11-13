package markup;

import java.util.EnumMap;
import java.util.Map;

public enum ContentType {
    EMPHASIS, STRIKEOUT, STRONG, TEXT, PARAGRAPH, HEADER,
    EMPTY_ELEMENT, CODE, INSERT, DELETE,
    LIST_ITEM, ORDERED_LIST, UNORDERED_LIST;

    private final static EnumMap<ContentType, String> CONTENT_TYPE_TO_OPEN_MARKDOWN_TAG =
            new EnumMap(Map.of(
                    EMPHASIS, "*",
                    STRIKEOUT, "~",
                    STRONG, "__",
                    PARAGRAPH, "",
                    HEADER, "#",
                    EMPTY_ELEMENT, "",
                    CODE, "`",
                    INSERT, "<<",
                    DELETE, "}}"
            ));

    private final static EnumMap<ContentType, String> CONTENT_TYPE_TO_CLOSE_MARKDOWN_TAG;

    static {
        CONTENT_TYPE_TO_CLOSE_MARKDOWN_TAG = new EnumMap<>(ContentType.class);
        CONTENT_TYPE_TO_CLOSE_MARKDOWN_TAG.putAll(CONTENT_TYPE_TO_OPEN_MARKDOWN_TAG);
        CONTENT_TYPE_TO_CLOSE_MARKDOWN_TAG.put(HEADER, "");
        CONTENT_TYPE_TO_CLOSE_MARKDOWN_TAG.put(INSERT, ">>");
        CONTENT_TYPE_TO_CLOSE_MARKDOWN_TAG.put(DELETE, "{{");
    }

    public final static EnumMap<ContentType, String> CONTENT_TYPE_TO_HTML_TAG =
            new EnumMap(Map.ofEntries(
                    Map.entry(EMPHASIS, "em"),
                    Map.entry(STRIKEOUT, "s"),
                    Map.entry(STRONG, "strong"),
                    Map.entry(TEXT, ""),
                    Map.entry(PARAGRAPH, "p"),
                    Map.entry(HEADER, "h"),
                    Map.entry(EMPTY_ELEMENT, ""),
                    Map.entry(CODE, "code"),
                    Map.entry(INSERT, "ins"),
                    Map.entry(DELETE, "del"),
                    Map.entry(LIST_ITEM, "li"),
                    Map.entry(ORDERED_LIST, "ol"),
                    Map.entry(UNORDERED_LIST, "ul")
            ));

    public static String getOpenMarkdownTag(ContentType item) {
        return getTag(CONTENT_TYPE_TO_OPEN_MARKDOWN_TAG, item, " hasn't open markdown tag");
    }

    public static String getCloseMarkdownTag(ContentType item) {
        return getTag(CONTENT_TYPE_TO_CLOSE_MARKDOWN_TAG, item, " hasn't close markdown tag");
    }

    public static String getOpenHtmlTag(ContentType item) {
        String htmlTag = getHtmlTag(item);
        if (!htmlTag.equals("")) {
            htmlTag = "<" + htmlTag + ">";
        }
        return htmlTag;
    }

    public static String getCloseHtmlTag(ContentType item) {
        String htmlTag = getHtmlTag(item);
        if (!htmlTag.equals("")) {
            htmlTag = "</" + htmlTag + ">";
        }
        return htmlTag;
    }

    private static String getHtmlTag(ContentType item) {
        return getTag(CONTENT_TYPE_TO_HTML_TAG, item, " hasn't html tag");
    }

    private static String getTag(
            EnumMap<ContentType, String> itemToString,
            ContentType item,
            String errorMessage) {
        String tag = itemToString.get(item);
        if (tag != null) {
            return tag;
        }

        throw new UnsupportedOperationException(item + errorMessage);
    }
}
