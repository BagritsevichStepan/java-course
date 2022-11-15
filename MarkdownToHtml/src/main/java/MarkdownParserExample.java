import markup.*;
import md2html.FileSource;
import md2html.MarkdownParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MarkdownParserExample {
    public static void main(String[] args) {
        List<Paragraphable> content = new ArrayList<>();
        try {
            FileSource source = new FileSource(new FileInputStream(args[0]), "UTF8");
            try {
                content = new MarkdownParser(source).parse();
            } finally {
                source.close();
            }
        } catch (IOException e) {
            System.out.println("Input file error: " + e.getMessage());
        }

        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8);
            try {
                for (Paragraphable block : content) {
                    StringBuilder html = new StringBuilder();
                    block.toHtml(html);

                    writer.write(html.toString());
                    writer.write(System.lineSeparator());
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Output file error: " + e.getMessage());
        }
    }
}
