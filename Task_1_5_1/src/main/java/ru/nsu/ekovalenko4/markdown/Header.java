package ru.nsu.ekovalenko4.markdown;

import java.util.Objects;

public final class Header extends Element {
    private final int level;
    private final Text content;

    public Header(int level, Text content) {
        if (level < 1) level = 1;
        if (level > 6) level = 6;
        this.level = level;
        this.content = content;
    }

    @Override
    public String toMarkdown() {
        return "#".repeat(level) + " " + content.toMarkdown();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Header other)) return false;
        return level == other.level && content.equals(other.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, content);
    }

    public int level() {
        return level;
    }

    public Text text() {
        return content;
    }
}
