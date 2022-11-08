import markup.*;
import md2html.CharSource;
import md2html.FileSource;
import md2html.MarkdownParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ParagraphExample {
    public static void main(String[] args) {
        // Paragraph example
        Paragraph paragraph = new Paragraph(List.of(
                new Strong(List.of(
                        new Text("1"),
                        new Strikeout(List.of(
                                new Text("2"),
                                new Emphasis(List.of(
                                        new Text("3"),
                                        new Text("4")
                                )),
                                new Text("5")
                        )),
                        new Text("6")
                ))
        ));

        StringBuilder paragraphInMarkdown = new StringBuilder();
        paragraph.toMarkdown(paragraphInMarkdown);
        System.out.println(paragraphInMarkdown);

        StringBuilder paragraphInHtml = new StringBuilder();
        paragraph.toHtml(paragraphInHtml);
        System.out.println(paragraphInHtml);
    }
}
