package ru.nsu.ekovalenko4.markdown;

public class Main {
    public static void main(String[] args) {
        Header header = new Header(1, Text.builder("Markdown Demo").bold().build());

        Text paragraph = Text.builder("This is an example of ").build();
        Text bold = Text.builder("bold").bold().build();
        Text italic = Text.builder("italic").italic().build();
        Text code = Text.builder("inline code").code().build();

        Link link = new Link(Text.builder("Markdown").build(),
                "https://en.wikipedia" +
                ".org/wiki/Markdown");
        Image image = new Image("Markdown Logo",
                "https://en.wikipedia.org/wiki/Markdown#/media/File:Markdown-mark.svg",
                "Logo");

        MDList.Unordered ul = new MDList.Unordered.Builder()
                .add(Text.builder("Item 1").build())
                .add(Text.builder("Item 2").italic().build())
                .add(Text.builder("Item 3").bold().build())
                .build();

        MDList.Ordered ol = new MDList.Ordered.Builder()
                .add(Text.builder("First").build())
                .add(Text.builder("Second").build())
                .add(Text.builder("Third").build())
                .build();

        Task task1 = new Task(false, Text.builder("Learn Java").build());
        Task task2 = new Task(true, Text.builder("Write Markdown library").build());

        Table table = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_CENTER, Table.ALIGN_RIGHT)
                .addRow("Name", "Score", "Rank")
                .addRow("Alice", 95, 1)
                .addRow("Bob", 87, 2)
                .addRow("Charlie", 78, 3)
                .build();

        Quote quote = new Quote.Builder()
                .add(Text.builder("This is a famous quote.").italic().build())
                .add(Text.builder("— Author").build())
                .build();

        CodeBlock codeBlock = new  CodeBlock("java",
                "public static void main(String[] args) {\n" +
                        "    System.out.println(\"Hello, Markdown!\");\n" +
                        "}");

        String md = header.toMarkdown() + "\n\n" +
                paragraph.toMarkdown() + " " +
                bold.toMarkdown() + " " +
                italic.toMarkdown() + " " +
                code.toMarkdown() + "\n\n" +
                link.toMarkdown() + "\n\n" +
                image.toMarkdown() + "\n\n" +
                "Unordered List:\n" + ul.toMarkdown() + "\n\n" +
                "Ordered List:\n" + ol.toMarkdown() + "\n\n" +
                "Tasks:\n" +
                task1.toMarkdown() + "\n" +
                task2.toMarkdown() + "\n\n" +
                "Table:\n" + table.toMarkdown() + "\n\n" +
                "Quote:\n" + quote.toMarkdown() + "\n\n" +
                "Code Block:\n" + codeBlock.toMarkdown();

        System.out.println(md);
    }

}
