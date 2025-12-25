package ru.nsu.ekovalenko4.markdown;

import java.util.Objects;

/**
 * Represents a Markdown task (checkbox) with checked state and text.
 */
public final class Task extends Element {
    private final boolean checked;
    private final Text content;

    public Task(boolean checked, Text content) {
        this.checked = checked;
        this.content = content;
    }

    @Override
    public String toMarkdown() {
        return "- [" + (checked ? "x" : " ") + "] " + content.toMarkdown();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Task other)) {
            return false;
        }
        return checked == other.checked && Objects.equals(content, other.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checked, content);
    }

    public boolean isChecked() {
        return checked;
    }

    public Text text() {
        return content;
    }

}
