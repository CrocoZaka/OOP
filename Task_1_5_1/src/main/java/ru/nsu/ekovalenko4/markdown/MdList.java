package ru.nsu.ekovalenko4.markdown;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Markdown list.
 * Supports ordered and unordered list types.
 */
public class MdList extends Element {

    /**
     * List type specification.
     */
    public enum Type {
        ORDERED,
        UNORDERED
    }

    private final Type type;
    private final List<Element> items;

    private MdList(Type type, List<Element> items) {
        this.type = type;
        this.items = List.copyOf(items);
    }

    @Override
    public String toMarkdown() {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (Element item : items) {
            String[] lines = item.toMarkdown().split("\n");
            for (String line : lines) {
                if (type == Type.ORDERED) {
                    sb.append(index++).append(". ").append(line).append("\n");
                } else {
                    sb.append("- ").append(line).append("\n");
                }
            }
        }
        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MdList other)) {
            return false;
        }
        return type == other.type && items.equals(other.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, items);
    }

    /**
     * Builder for MDList.
     */
    public static final class Builder {
        private Type type;
        private final List<Element> items = new ArrayList<>();

        /**
         * Specifies ordered list type.
         */
        public Builder ordered() {
            this.type = Type.ORDERED;
            return this;
        }

        /**
         * Specifies unordered list type.
         */
        public Builder unordered() {
            this.type = Type.UNORDERED;
            return this;
        }

        public Builder add(Element e) {
            items.add(e);
            return this;
        }

        private void check() {
            if (type == null) {
                throw new IllegalArgumentException("List type must be specified");
            }
            if (items.isEmpty()) {
                throw new IllegalArgumentException("List cannot be empty");
            }
        }

        public MdList build() {
            check();
            return new MdList(type, items);
        }
    }
}

