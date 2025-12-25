package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for Text.
 */
public class TextTest {

    @Test
    void testPlainText() {
        Text text = Text.builder("Plain").build();
        assertEquals("Plain", text.toMarkdown());
    }

    @Test
    void testBoldText() {
        Text text = Text.builder("Bold").bold().build();
        assertEquals("**Bold**", text.toMarkdown());
    }

    @Test
    void testItalicText() {
        Text text = Text.builder("Italic").italic().build();
        assertEquals("*Italic*", text.toMarkdown());
    }

    @Test
    void testStrikethroughText() {
        Text text = Text.builder("Strike").strikethrough().build();
        assertEquals("~~Strike~~", text.toMarkdown());
    }

    @Test
    void testCodeText() {
        Text text = Text.builder("code()").code().build();
        assertEquals("`code()`", text.toMarkdown());
    }

    @Test
    void testMultipleStyles() {
        Text text = Text.builder("Multi").bold().italic().strikethrough().build();
        assertEquals("***~~Multi~~***", text.toMarkdown());
    }

    @Test
    void testStyledCode() {
        assertThrows(IllegalArgumentException.class,
                () -> Text.builder("x").code().bold().build()
        );
    }

    @Test
    void testEmptyContentThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Text.builder("").build());
        assertThrows(IllegalArgumentException.class, () -> Text.builder("   ").build());
        assertThrows(IllegalArgumentException.class, () -> Text.builder(null).build());
    }

    @Test
    void testEscapePipeCharacter() {
        Text text = Text.builder("a|b").build();
        assertEquals("a\\|b", text.toMarkdown());
    }

    @Test
    void testEqualsAndHash() {
        Text t1 = Text.builder("Bold").bold().build();
        Text t2 = Text.builder("Bold").bold().build();
        Text t3 = Text.builder("Italic").italic().build();
        Header h1 = new Header(1, t1);

        assertAll(
            () -> assertEquals(t1, t1),
            () -> assertEquals(t1, t2),
            () -> assertEquals(t1.hashCode(), t2.hashCode()),
            () -> assertNotEquals(t1, t3),
            () -> assertNotEquals(t1, h1)
        );
    }
}
