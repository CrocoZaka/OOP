package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for Table.
 */
class TableTest {

    @Test
    void testSimpleTable() {
        Table table = new Table.Builder()
                .addRow(
                        Text.builder("A").build(),
                        Text.builder("B").build()
                )
                .addRow(
                        Text.builder("1").build(),
                        Text.builder("2").build()
                )
                .build();
        assertEquals(
                "| A | B |\n"
                        + "| :--- | :--- |\n"
                        + "| 1 | 2 |",
                table.toMarkdown()
        );
    }

    @Test
    void testTableWithAlignments() {
        Table table = new Table.Builder()
                .withAlignments(
                        Table.ALIGN_LEFT,
                        Table.ALIGN_CENTER,
                        Table.ALIGN_RIGHT
                )
                .addRow("A", "B", "C")
                .addRow("1", "2", "3")
                .build();
        assertEquals(
                "| A | B | C |\n"
                        + "| :--- | :---: | ---: |\n"
                        + "| 1 | 2 | 3 |",
                table.toMarkdown()
        );
    }

    @Test
    void testRowLimit() {
        Table table = new Table.Builder()
                .withRowLimit(1)
                .addRow("A", "B")
                .addRow("1", "2")
                .addRow("3", "4")
                .build();
        assertEquals(
                "| A | B |\n"
                        + "| :--- | :--- |\n"
                        + "| 1 | 2 |",
                table.toMarkdown()
        );
    }

    @Test
    void testEqualsAndHashCode() {
        Table t1 = new Table.Builder()
                .addRow("A", "B")
                .addRow("1", "2")
                .build();

        Table t2 = new Table.Builder()
                .addRow("A", "B")
                .addRow("1", "2")
                .build();

        Table t3 = new Table.Builder()
                .withAlignments(
                        Table.ALIGN_CENTER,
                        Table.ALIGN_RIGHT
                )
                .addRow("A", "Z")
                .addRow("1", "9")
                .build();
        Text text = Text.builder("Text").build();

        assertAll(
                () -> assertEquals(t1, t1),
                () -> assertEquals(t1, t2),
                () -> assertEquals(t1.hashCode(), t2.hashCode()),
                () -> assertNotEquals(t1, t3),
                () -> assertNotEquals(t1, text)
        );
    }

    @Test
    void testBuildWithoutHeaderThrowsException() {
        Table.Builder builder = new Table.Builder();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, builder::build);
    }

    @Test
    void testWrongAlignmentCountThrowsException() {
        Table.Builder builder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_RIGHT)
                .addRow("A");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, builder::build);
    }
}
