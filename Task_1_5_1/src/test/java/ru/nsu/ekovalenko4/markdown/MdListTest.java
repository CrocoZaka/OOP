package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class MdListTest {

    @Test
    void testUnorderedListSimple() {
        MdList.Unordered list = new MdList.Unordered.Builder()
                .add(Text.builder("A").build())
                .add(Text.builder("B").build())
                .build();
        assertEquals("- A\n- B", list.toMarkdown()
        );
    }

    @Test
    void testOrderedListSimple() {
        MdList.Ordered list = new MdList.Ordered.Builder()
                .add(Text.builder("A").build())
                .add(Text.builder("B").build())
                .build();
        assertEquals("1. A\n2. B", list.toMarkdown());
    }

    @Test
    void testUnorderedListWithMultilineElement() {
        MdList.Unordered list = new MdList.Unordered(List.of(Text.builder("A\nB").build()));
        assertEquals("- A\n- B", list.toMarkdown());
    }

    @Test
    void testOrderedListWithMultilineElement() {
        MdList.Ordered list = new MdList.Ordered(List.of(Text.builder("A\nB").build()));
        assertEquals("1. A\n2. B", list.toMarkdown());
    }

    @Test
    void testEqualsAndHashCodeUnordered() {
        MdList.Unordered l1 = new MdList.Unordered(
                List.of(Text.builder("A").build())
        );
        MdList.Unordered l2 = new MdList.Unordered(
                List.of(Text.builder("A").build())
        );
        MdList.Ordered l3 = new MdList.Ordered(
                List.of(Text.builder("A").build())
        );
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
    void testBuilderCreatesEquivalentList() {
        MdList.Unordered fromBuilder = new MdList.Unordered.Builder()
                .add(Text.builder("A").build())
                .add(Text.builder("B").build())
                .build();
        MdList.Unordered direct = new MdList.Unordered(
                List.of(
                        Text.builder("A").build(),
                        Text.builder("B").build()
                )
        );
        assertEquals(fromBuilder, direct);
    }

    @Test
    void testNullItemsList() {
        MdList.Unordered l1 = new MdList.Unordered(null);
        assertEquals("", l1.toMarkdown());
        MdList.Ordered l2 = new MdList.Ordered(null);
        assertEquals("", l2.toMarkdown());
    }
}
