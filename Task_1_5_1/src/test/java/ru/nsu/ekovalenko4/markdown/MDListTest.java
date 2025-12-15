package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import java.util.List;

class MDListTest {

    @Test
    void testUnorderedListSimple() {
        MDList.Unordered list = new MDList.Unordered.Builder()
                .add(Text.builder("A").build())
                .add(Text.builder("B").build())
                .build();
        assertEquals("- A\n- B", list.toMarkdown()
        );
    }

    @Test
    void testOrderedListSimple() {
        MDList.Ordered list = new MDList.Ordered.Builder()
                .add(Text.builder("A").build())
                .add(Text.builder("B").build())
                .build();
        assertEquals("1. A\n2. B", list.toMarkdown());
    }

    @Test
    void testUnorderedListWithMultilineElement() {
        MDList.Unordered list = new MDList.Unordered(List.of(Text.builder("A\nB").build()));
        assertEquals("- A\n- B", list.toMarkdown());
    }

    @Test
    void testOrderedListWithMultilineElement() {
        MDList.Ordered list = new MDList.Ordered(List.of(Text.builder("A\nB").build()));
        assertEquals("1. A\n2. B", list.toMarkdown());
    }

    @Test
    void testEqualsAndHashCodeUnordered() {
        MDList.Unordered l1 = new MDList.Unordered(
                List.of(Text.builder("A").build())
        );
        MDList.Unordered l2 = new MDList.Unordered(
                List.of(Text.builder("A").build())
        );
        MDList.Ordered l3 = new MDList.Ordered(
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
        MDList.Unordered fromBuilder = new MDList.Unordered.Builder()
                .add(Text.builder("A").build())
                .add(Text.builder("B").build())
                .build();
        MDList.Unordered direct = new MDList.Unordered(
                List.of(
                        Text.builder("A").build(),
                        Text.builder("B").build()
                )
        );
        assertEquals(fromBuilder, direct);
    }

    @Test
    void testNullItemsList() {
        MDList.Unordered l1 = new MDList.Unordered(null);
        assertEquals("", l1.toMarkdown());
        MDList.Ordered l2 = new MDList.Ordered(null);
        assertEquals("", l2.toMarkdown());
    }
}
