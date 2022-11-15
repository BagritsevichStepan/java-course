# Markdown parser
____
The project implements text markup,
elements of which can be converted to **markdown** or **html**.
It can also **parse markdown** into text markup,
which can then be converted into html code.

## Problem statement
____
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

### Second part
something

## Usage
____
something

## Examples
____
### First part
something
### Second part
something