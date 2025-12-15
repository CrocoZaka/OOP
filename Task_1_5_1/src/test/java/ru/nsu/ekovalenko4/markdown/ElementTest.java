package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ElementTest {

    @Test
    void testElement() {
        Element t = Text.builder("text").build();
        assertEquals(t.toMarkdown(), t.toString());
    }

}