package ru.nsu.ekovalenko4.markdown;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Quote extends Element {
    private final List<Element> items;

    public Quote(List<Element> items) {
        this.items = List.copyOf(items);
    }

    @Override
    public String toMarkdown() {
        StringBuilder sb = new StringBuilder();
        for (Element e : items) {
            String[] lines = e.toMarkdown().split("\n");
            for (String line : lines) {
                sb.append("> ").append(line).append("\n");
            }
        }
        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quote other)) return false;
        return Objects.equals(items, other.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    public static class Builder {
        private final List<Element> items = new ArrayList<>();
        public Builder add(Element e) {
            items.add(e);
            return this;
        }
        public Quote build() {
            return new Quote(items);
        }
    }
}