package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void testUncheckedTask() {
        Text text = Text.builder("Do homework").build();
        Task task = new Task(false, text);
        assertEquals("- [ ] Do homework", task.toMarkdown());
    }

    @Test
    void testCheckedTask() {
        Text text = Text.builder("Done").build();
        Task task = new Task(true, text);
        assertEquals("- [x] Done", task.toMarkdown());
    }

    @Test
    void testFormattedTextPreserved() {
        Text text = Text.builder("Important").bold().build();
        Task task = new Task(false, text);
        assertEquals("- [ ] **Important**", task.toMarkdown());
    }

    @Test
    void testEqualsAndHashCode() {
        Text t1 = Text.builder("Task").build();
        Text t2 = Text.builder("Task").build();
        Text t3 = Text.builder("Other").build();
        Task task1 = new Task(true, t1);
        Task task2 = new Task(true, t2);
        Task task3 = new Task(false, t3);

        assertAll(
                () -> assertEquals(task1, task1),
                () -> assertEquals(task1, task2),
                () -> assertEquals(task1.hashCode(), task2.hashCode()),
                () -> assertNotEquals(task1, task3),
                () -> assertNotEquals(task1, t1)
        );
    }

    @Test
    void testAccessors() {
        Text text = Text.builder("Read book").build();
        Task task = new Task(true, text);

        assertTrue(task.isChecked());
        assertEquals(text, task.text());
    }
}
