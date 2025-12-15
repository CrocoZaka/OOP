package ru.nsu.ekovalenko4.markdown;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class MDList extends Element {
    protected final List<Element> items;

    protected MDList(List<Element> items) {
        this.items = items == null ? List.of() : List.copyOf(items);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MDList other)) return false;
        return Objects.equals(items, other.items) && this.getClass().equals(other.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, getClass());
    }

    public static class Unordered extends MDList {
        public Unordered(List<Element> items) { super(items); }

        @Override
        public String toMarkdown() {
            StringBuilder sb = new StringBuilder();
            for (Element e : items) {
                String[] lines = e.toMarkdown().split("\n");
                for (String line : lines) {
                    sb.append("- ").append(line).append("\n");
                }
            }
            if (!sb.isEmpty()) sb.setLength(sb.length() - 1);
            return sb.toString();
        }

        public static class Builder {
            private final List<Element> items = new ArrayList<>();
            public Builder add(Element e) { items.add(e); return this; }
            public Unordered build() { return new Unordered(items); }
        }
    }

    public static class Ordered extends MDList {
        public Ordered(List<Element> items) { super(items); }

        @Override
        public String toMarkdown() {
            StringBuilder sb = new StringBuilder();
            int index = 1;
            for (int i = 0; i < items.size(); i++) {
                Element e = items.get(i);
                String[] lines = e.toMarkdown().split("\n");
                for (String line : lines) {
                    sb.append(index++).append(". ").append(line).append("\n");
                }
            }
            if (!sb.isEmpty()) sb.setLength(sb.length() - 1);
            return sb.toString();
        }

        public static class Builder {
            private final List<Element> items = new ArrayList<>();
            public Builder add(Element e) {
                items.add(e);
                return this;
            }
            public Ordered build() { return new Ordered(items); }
        }
    }
}
