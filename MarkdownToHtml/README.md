# Markdown parser
The project implements text markup,
elements of which can be converted to **markdown** or **html**.
It can also **parse markdown** into text markup,
which can then be converted into html code.

## Problem statement
1. [First part. Text markup](#first-part)
2. [Second part. Markdown to Html. Markdown parser](#second-part)

### First part
1. Develop a set of classes for *text markup*.
2. The `Paragraph` class can contain any number of other markup elements and text elements.
3. The `Text` class is a text element.
4. Other elements of text markup:
   + `Header` is a title or subtitle;
   + `Emphasis` italicizes some text;
   + `Strong` makes a text bold;
   + `Strikeout` crosses out a text;
   + `Ordered list` is an ordered list that contains list items;
   + `Unordered list` is an unordered list that contains list items;
   + `ListItem` is a list item that can contain a sequence of paragraphs and lists;
   + `Code` denotes word or phrase as code;
   + `Insert` defines a text that has been inserted;
   + `Delete` marks deleted text.
5. Markup elements can contain any number of other markup elements and text elements.
6. Classes must implement the `toMarkdown(StringBuilder)` method, which must generate markdown according to the following rules:
   + **_Text elements_** and **_paragraph_** are rendered as is;
   + **_Header_** has a **'#'** symbol in front of it. The number of these symbols corresponds to the header level;
   + **_Italic text_** is surrounded by **'*'** symbols;
   + **_Bold text_** is surrounded by **'__'** symbols;
   + **_Strikethrough text_** is surrounded by **'~'** symbols;
   + **_Code_** is surrounded by **'`'** symbols;
   + **_Inserted text_** is surrounded by **'<<'** _(open tag)_ and **'>>'** _(close tag)_ symbols;
   + **_Deleted text_** is surrounded by **'}}'** _(open tag)_ and **'{{'** _(close tag)_ symbols;
   + **_Ordered and unordered lists_** and also **_list items_** do not support the `toMarkdown(StringBuilder)` method.
7. Classes must implement the `toHtml(StringBuilder)` method, which must generate html code according to the following rules:
   + `Text` has no html tag;
   + `Paragraph` has the tag `<p>`;
   + `Header` is defined with the `<h(title level)>` tag;
   + `Emphasis` is a text with the `<em>` tag;
   + `Strong` has the tag `<strong>`;
   + `Strikeout` is defined with the `<s>` tag;
   + `Ordered list`, `Unordered list` and `ListItem` have the `<ol>`, `<ul>` and `<li>` tags respectively;
   + `Code` is defined with the `<code>` tag;
   + `Insert` has the tag `<ins>`;
   + `Delete` is a text with the `<del>` tag.
8. The following code should compile successfully:
```
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
```
The call `paragraph.toMarkdown(new StringBuilder())` should fill the passed `StringBuilder` with the following content:

`__1~2*34*5~6__`

The call `paragraph.toHtml(new StringBuilder())` should fill the passed `StringBuilder` with the following content:

`<p><strong>1<s>2<em>34</em>5</s>6</strong></p>`

9. Markup classes must be in the package `markup`

### Second part
1. Implement a Markdown parser
2. The parser must adhere to the following rules:
   + Paragraphs are separated by blank lines
   + Other markup elements are marked in the same way as in the `toMarkdown` method
3. The converter can store the original and converted data in memory
4. Parser classes must be in the package `md2html`
5. The other rules are given in the example below:
   + Input file:
```
# First level header

## Second level header

### Third ## level header

#### Fourth level header
## Still fourth ##

This paragraph of text,
## contains two lines.

    # It might look like a headline.
But no, it's a paragraph starting with `#.

#And it's not a headline.

###### Headlines can be multi-line.
(and with skipping headings on previous levels).

We all like to *select* text in _different_ ways.
**Highlighting**, is used much less frequently,
but __why not__?
A little --drawing out-- has never hurt anyone.
The code is represented by the `code` element.

Notice how the special
HTML characters such as `<`, `>` and `&`.

Did you know that in Markdown, single * and _
do not stand for selection?
They can also be escaped
with a backslash: \*.



Unnecessary empty lines should be ignored.

Do you love *nested __selections__*
like __--I do--__?
```
+ Output file (Html-code):
```
<h1>First level header</h1>
<h2>Second level header</h2>
<h3>Third ## level header</h3>
<h4>Fourth level header
## Still fourth ##</h4>
<p>This paragraph of text,
## contains two lines.</p>
<p>    # It might look like a headline.
But no, it's a paragraph starting with `#.</p>
<p>#And it's not a headline.</p>
<h6>Headlines can be multi-line.
(and with skipping headings on previous levels).</h6>
<p>We all like to <em>select</em> text in <em>different</em> ways.
<strong>Highlighting</strong>, is used much less frequently,
but <strong>why not</strong>?
A little <s>drawing out</s> has never hurt anyone.
The code is represented by the <code>code</code> element.</p>
<p>Notice how the special
HTML characters such as <code>&lt;</code>, <code>&gt;</code> and <code>&amp;</code>.</p>
<p>Did you know that in Markdown, single * and _
do not stand for selection?
They can also be escaped
with a backslash: *.</p>
<p>Unnecessary empty lines should be ignored.</p>
<p>Do you love <em>nested <strong>selections</strong></em>
like <strong><s>I do</s></strong>?</p>
```
+ Result in browser

## Examples
### First part
something
### Second part
something
