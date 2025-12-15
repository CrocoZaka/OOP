package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LinkTest {

    @Test
    void testSimpleLink() {
        Text text = Text.builder("Google").build();
        Link link = new Link(text, "https://google.com");
        assertEquals("[Google](https://google.com)", link.toMarkdown());
    }

    @Test
    void testLinkWithTitle() {
        Text text = Text.builder("Google").build();
        Link link = new Link(text, "https://google.com", "Search engine");
        assertEquals("[Google](https://google.com \"Search engine\")", link.toMarkdown());
    }

    @Test
    void testTitleIsEmptyString() {
        Text text = Text.builder("Google").build();
        Link link = new Link(text, "https://google.com", "");
        assertEquals("[Google](https://google.com)", link.toMarkdown());
    }

    @Test
    void testContentFormattingPreserved() {
        Text text = Text.builder("Google").bold().italic().build();
        Link link = new Link(text, "https://google.com");
        assertEquals("[***Google***](https://google.com)", link.toMarkdown());
    }

    @Test
    void testUrlCannotBeNull() {
        Text text = Text.builder("Google").build();
        assertThrows(IllegalArgumentException.class, () -> new Link(text, null));
        assertThrows(IllegalArgumentException.class, () -> new Link(text, "   "));
    }

    @Test
    void testEqualsAndHashCode() {
        Text t1 = Text.builder("Google").build();
        Text t2 = Text.builder("Google").build();
        Text t3 = Text.builder("Yandex").build();
        Link l1 = new Link(t1, "https://google.com", "Search engine");
        Link l2 = new Link(t2, "https://google.com", "Search engine");
        Link l3 = new Link(t3, "https://yandex.ru/", "Search engine");

        assertAll(
                () -> assertEquals(l1, l1),
                () -> assertEquals(l1, l2),
                () -> assertEquals(l1.hashCode(), l2.hashCode()),
                () -> assertNotEquals(l1, l3),
                () -> assertNotEquals(l1, t1)
        );
    }

    @Test
    void testAccessors() {
        Text text = Text.builder("Google").build();
        Link link = new Link(text, "https://google.com", "Search engine");
        assertEquals(text, link.text());
        assertEquals("https://google.com", link.url());
        assertEquals("Search engine", link.title());
    }
}
