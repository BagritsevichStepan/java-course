package md2html;

import markup.*;

import java.io.IOException;
import java.util.*;

public class MarkdownParser extends BaseParser {
    private int[] openTagsCount;

    private static final List<String> OPEN_MARKDOWN_TAG = new ArrayList<>(
            List.of("**", "__", "--", "<<", "}}", "*", "_", "`")
    );
    private static final Map<String, ContentType> CLOSE_MARKDOWN_TAG_TO_CONTENT_TYPE;

    static {
        CLOSE_MARKDOWN_TAG_TO_CONTENT_TYPE = new LinkedHashMap<>();
        CLOSE_MARKDOWN_TAG_TO_CONTENT_TYPE.put("**", ContentType.STRONG);
        CLOSE_MARKDOWN_TAG_TO_CONTENT_TYPE.put("__", ContentType.STRONG);
        CLOSE_MARKDOWN_TAG_TO_CONTENT_TYPE.put("--", ContentType.STRIKEOUT);
        CLOSE_MARKDOWN_TAG_TO_CONTENT_TYPE.put(">>", ContentType.INSERT);
        CLOSE_MARKDOWN_TAG_TO_CONTENT_TYPE.put("{{", ContentType.DELETE);
        CLOSE_MARKDOWN_TAG_TO_CONTENT_TYPE.put("*", ContentType.EMPHASIS);
        CLOSE_MARKDOWN_TAG_TO_CONTENT_TYPE.put("_", ContentType.EMPHASIS);
        CLOSE_MARKDOWN_TAG_TO_CONTENT_TYPE.put("`", ContentType.CODE);
    }

    public MarkdownParser(CharSource source) throws IOException {
        super(source);
    }

    public List<Paragraphable> parse() throws IOException {
        List<Paragraphable> content = new ArrayList<>();
        while (!fileEnded()) {
            skipEmptyLines();
            content.add(parseHeaderOrParagraph());
            skipEmptyLines();
        }
        return content;
    }

    private Paragraphable parseHeaderOrParagraph() throws IOException {
        int headerLevel = 0;
        StringBuilder returnPart = new StringBuilder();
        while (test(ContentType.getOpenMarkdownTag(ContentType.HEADER))) {
            returnPart.append(ContentType.getOpenMarkdownTag(ContentType.HEADER));
            headerLevel++;
        }

        if (headerLevel > 0 && testWhitespace()) {
            return new Header(parseText(), headerLevel);
        }

        returnSourcePart(returnPart.toString());
        return new Paragraph(parseText());
    }

    private List<Paragraphable> parseText() throws IOException {
        openTagsCount = new int[OPEN_MARKDOWN_TAG.size()];
        return List.of(parseText(-1));
    }

    private Paragraphable parseText(int curTag) throws IOException {
        StringBuilder sb = new StringBuilder();
        List<Paragraphable> content = new ArrayList<>();

        while (!fileEnded() && !(isLineSeparator(prevChar) && isLineSeparator(curChar))) {
            if (prevChar == '\\') {
                sb.setCharAt(sb.length() - 1, curChar);
                nextChar();
                continue;
            }

            int tagIndex = 0;
            boolean isText = true;
            for (Map.Entry<String, ContentType> closeTag :
                    CLOSE_MARKDOWN_TAG_TO_CONTENT_TYPE.entrySet()) {
                if (openTagsCount[tagIndex] > 0 && test(closeTag.getKey())) {
                    if (tagIndex == curTag) {
                        openTagsCount[tagIndex]--;
                        content.add(new Text(sb.toString()));
                        sb.setLength(0);
                        return buildTextStyle(content, closeTag.getValue());
                    } else {
                        returnSourcePart(closeTag.getKey());
                        content.add(0, new Text(OPEN_MARKDOWN_TAG.get(tagIndex)));
                        return new EmptyElement(content);
                    }
                } else if (test(OPEN_MARKDOWN_TAG.get(tagIndex))) {
                    content.add(new Text(sb.toString()));
                    sb.setLength(0);

                    openTagsCount[tagIndex]++;
                    content.add(parseText(tagIndex));

                    isText = false;
                    break;
                }
                tagIndex++;
            }

            if (isText) {
                sb.append(curChar);
                nextChar();
            }
        }

        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
            content.add(new Text(sb.toString()));
            sb.setLength(0);
        }

        if (curTag != -1 && openTagsCount[curTag] > 0) {
            openTagsCount[curTag]--;
            content.add(0, new Text(OPEN_MARKDOWN_TAG.get(curTag)));
        }

        return new EmptyElement(content);
    }

    private Paragraphable buildTextStyle(List<Paragraphable> content, ContentType item) {
        return switch (item) {
            case EMPHASIS -> new Emphasis(content);
            case STRONG -> new Strong(content);
            case STRIKEOUT -> new Strikeout(content);
            case INSERT -> new Insert(content);
            case DELETE -> new Delete(content);
            case CODE -> new Code(content);
            default -> throw new UnsupportedOperationException("Unsupported text style " + item);
        };
    }
}
