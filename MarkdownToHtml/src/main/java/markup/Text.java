package markup;

import java.util.Map;

public class Text implements Content {
    private final String text;

    private final static Map<Character, String> HTML_ENTITY = Map.of(
            '<', "&lt;",
            '>', "&gt;",
            '&', "&amp;"
    );

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder markdown) {
        markdown.append(text);
    }

    @Override
    public void toHtml(StringBuilder html) {
        for (char c : text.toCharArray()) {
            html.append(HTML_ENTITY.getOrDefault(c, Character.toString(c)));
        }
    }
}
