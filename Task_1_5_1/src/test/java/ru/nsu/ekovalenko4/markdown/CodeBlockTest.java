package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for CodeBlock.
 */
class CodeBlockTest {

    @Test
    void testAccessors() {
        CodeBlock block = new CodeBlock("java", "int x = 10;");
        assertEquals("java", block.language());
        assertEquals("int x = 10;", block.code());
    }

    @Test
    void testCodeBlockWithoutLanguage() {
        CodeBlock block = new CodeBlock(null, "int x = 10;");
        assertEquals("```\nint x = 10;\n```", block.toMarkdown());
        assertEquals("", block.language());
    }

    @Test
    void testCodeBlockWithLanguage() {
        CodeBlock block = new CodeBlock("java", "int x = 10;");
        assertEquals("```java\nint x = 10;\n```", block.toMarkdown());
    }

    @Test
    void testEmptyCode() {
        CodeBlock block1 = new CodeBlock("java", "");
        assertEquals("```java\n\n```", block1.toMarkdown());
        CodeBlock block2 = new CodeBlock("java", null);
        assertEquals("```java\n\n```", block2.toMarkdown());
        assertEquals("", block2.code());
    }

    @Test
    void testEqualsAndHashCode() {
        CodeBlock b1 = new CodeBlock("java", "code");
        CodeBlock b2 = new CodeBlock("java", "code");
        CodeBlock b3 = new CodeBlock("c#", "code");
        Text t1 = Text.builder("Text").build();

        assertAll(
                () -> assertEquals(b1, b1),
                () -> assertEquals(b1, b2),
                () -> assertEquals(b1.hashCode(), b2.hashCode()),
                () -> assertNotEquals(b1, b3),
                () -> assertNotEquals(b1, t1)
        );
    }

}
