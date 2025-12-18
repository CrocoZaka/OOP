package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for MdList.
 */
class MdListTest {

    @Test
    void testUnorderedListSimple() {
        MdList list = new MdList.Builder()
                .add(Text.builder("A").build())
                .add(Text.builder("B").build())
                .unordered()
                .build();
        assertEquals("- A\n- B", list.toMarkdown()
        );
    }

    @Test
    void testOrderedListSimple() {
        MdList list = new MdList.Builder()
                .add(Text.builder("A").build())
                .add(Text.builder("B").build())
                .ordered()
                .build();
        assertEquals("1. A\n2. B", list.toMarkdown());
    }

    @Test
    void testUnorderedListWithMultilineElement() {
        MdList list = new MdList.Builder().add(Text.builder("A\nB").build()).unordered()
                .build();
        assertEquals("- A\n- B", list.toMarkdown());
    }

    @Test
    void testOrderedListWithMultilineElement() {
        MdList list = new MdList.Builder().add(Text.builder("A\nB").build()).ordered()
                .build();
        assertEquals("1. A\n2. B", list.toMarkdown());
    }

    @Test
    void testEqualsAndHashCodeUnordered() {
        MdList l1 = new MdList.Builder().add(Text.builder("A").build()).unordered().build();
        MdList l2 = new MdList.Builder().add(Text.builder("A").build()).unordered().build();
        MdList l3 = new MdList.Builder().add(Text.builder("A").build()).ordered().build();
        Text t1 = Text.builder("Text").build();

        assertAll(
                () -> assertEquals(l1, l1),
                () -> assertEquals(l1, l2),
                () -> assertEquals(l1.hashCode(), l2.hashCode()),
                () -> assertNotEquals(l1, l3),
                () -> assertNotEquals(l1, t1)
        );
    }

    @Test
    void testNoTypeOrNoItems() {
        assertThrows(IllegalArgumentException.class, () -> new MdList.Builder().add(Text.builder(
                "A").build()).build());
        assertThrows(IllegalArgumentException.class, () -> new MdList.Builder().ordered().build());
    }
}
