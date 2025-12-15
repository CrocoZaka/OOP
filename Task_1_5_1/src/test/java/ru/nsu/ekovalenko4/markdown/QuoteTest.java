package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Quote.
 */
class QuoteTest {

    @Test
    void testSingleElementQuote() {
        Quote quote = new Quote(List.of(Text.builder("Quote").build()));
        assertEquals("> Quote", quote.toMarkdown());
    }

    @Test
    void testMultipleElements() {
        Quote quote = new Quote(
                List.of(
                        Text.builder("First").build(),
                        Text.builder("Second").build()
                )
        );
        String expected = """
                > First
                > Second
                """.trim();
        assertEquals(expected, quote.toMarkdown());
    }

    @Test
    void testMultilineElement() {
        Element multiline = Text.builder("First\nSecond").build();
        Quote quote = new Quote(List.of(multiline));
        String expected = """
                > First
                > Second
                """.trim();
        assertEquals(expected, quote.toMarkdown());
    }

    @Test
    void testFormattedTextInsideQuote() {
        Quote quote = new Quote(List.of(Text.builder("Quote").bold().build()));
        assertEquals("> **Quote**", quote.toMarkdown());
    }

    @Test
    void testEmptyQuoteFromEmptyList() {
        Quote quote = new Quote(List.of());
        assertEquals("", quote.toMarkdown());
    }

    @Test
    void testBuilder() {
        Quote quote = new Quote.Builder()
                .add(Text.builder("First").build())
                .add(Text.builder("Second").build())
                .build();
        String expected = """
                > First
                > Second
                """.trim();
        assertEquals(expected, quote.toMarkdown());
    }

    @Test
    void testEqualsAndHashCode() {
        Quote q1 = new Quote(
                List.of(Text.builder("Same").build())
        );
        Quote q2 = new Quote(
                List.of(Text.builder("Same").build())
        );
        Quote q3 = new Quote(
                List.of(Text.builder("Other").build())
        );
        Text t1 = Text.builder("Text").build();

        assertAll(
                () -> assertEquals(q1, q1),
                () -> assertEquals(q1, q2),
                () -> assertEquals(q1.hashCode(), q2.hashCode()),
                () -> assertNotEquals(q1, q3),
                () -> assertNotEquals(q1, t1)
        );
    }
}
