package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for Header.
 */
class HeaderTest {

    @Test
    void testSimpleHeader() {
        Header header = new Header(1, Text.builder("Title").build());
        assertEquals("# Title", header.toMarkdown());
    }

    @Test
    void testFormattedHeader() {
        Header header = new Header(3, Text.builder("Title").bold().build());
        assertEquals("### **Title**", header.toMarkdown());
    }

    @Test
    void testAccessors() {
        Text text = Text.builder("Title").build();
        Header header = new Header(4, text);
        assertEquals(4, header.level());
        assertEquals(text, header.text());
    }

    @Test
    void testHeaderLevelBounds() {
        Header header1 = new Header(0, Text.builder("Min").build());
        assertEquals(1, header1.level());
        assertEquals("# Min", header1.toMarkdown());

        Header header2 = new Header(10, Text.builder("Max").build());
        assertEquals(6, header2.level());
        assertEquals("###### Max", header2.toMarkdown());
    }

    @Test
    void testHeaderEqualsAndHashCode() {
        Header h1 = new Header(2, Text.builder("Same").italic().build());
        Header h2 = new Header(2, Text.builder("Same").italic().build());
        Header h3 = new Header(3, Text.builder("Different").italic().build());
        Text t1 = Text.builder("Text").build();
        assertAll(
                () -> assertEquals(h1, h1),
                () -> assertEquals(h1, h2),
                () -> assertEquals(h1.hashCode(), h2.hashCode()),
                () -> assertNotEquals(h1, h3),
                () -> assertNotEquals(h1, t1)
        );
    }
}
