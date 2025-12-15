package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ImageTest {

    @Test
    void testImageWithoutTitle() {
        Image image = new Image("alt text", "image.png", null);
        assertEquals("![alt text](image.png)", image.toMarkdown());
    }

    @Test
    void testImageWithTitle() {
        Image image = new Image("alt text", "image.png", "title");
        assertEquals("![alt text](image.png \"title\")", image.toMarkdown());
    }

    @Test
    void testEmptyTitle() {
        Image image = new Image("alt", "image.png", "");
        assertEquals("![alt](image.png)", image.toMarkdown());
    }

    @Test
    void testNullAlt() {
        Image image = new Image(null, "image.png", null);
        assertEquals("![](image.png)", image.toMarkdown());
        assertEquals("", image.alt());
    }

    @Test
    void testSrcCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Image("", null, null));
        assertThrows(IllegalArgumentException.class, () -> new Image("", "   ", null));
    }

    @Test
    void testEqualsAndHashCode() {
        Image i1 = new Image("alt", "src", "title");
        Image i2 = new Image("alt", "src", "title");
        Image i3 = new Image("alt", "other_src", "title");
        Text t1 = Text.builder("text").build();

        assertAll(
                () -> assertEquals(i1, i1),
                () -> assertEquals(i1, i2),
                () -> assertEquals(i1.hashCode(), i2.hashCode()),
                () -> assertNotEquals(i1, i3),
                () -> assertNotEquals(i1, t1)
        );
    }

    @Test
    void testAccessors() {
        Image image = new Image("alt", "src", "title");
        assertEquals("alt", image.alt());
        assertEquals("src", image.src());
        assertEquals("title", image.title());
    }
}
