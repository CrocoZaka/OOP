package ru.nsu.ekovalenko4.markdown;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

/**
 * Represents formatted text in Markdown.
 * Use Builder to create instances.
 */
public final class Text extends Element {

    /**
     * Enum class for formatting style names and markers.
     */
    public enum Style {
        CODE("`"),
        STRIKE("~~"),
        ITALIC("*"),
        BOLD("**");

        private final String marker;

        Style(String marker) {
            this.marker = marker;
        }

        public String marker() {
            return marker;
        }
    }

    private static final List<Style> RENDER_ORDER = List.of(
            Style.CODE,
            Style.STRIKE,
            Style.ITALIC,
            Style.BOLD
    );

    private final String content;
    private final EnumSet<Style> styles;

    private Text(Builder builder) {
        this.content = builder.content;
        this.styles = EnumSet.copyOf(builder.styles);
    }

    @Override
    public String toMarkdown() {
        String result = escape(content);
        for (Style style : RENDER_ORDER) {
            if (styles.contains(style)) {
                result = style.marker() + result + style.marker();
            }
        }
        return result;
    }

    private static String escape(String text) {
        return text.replace("|", "\\|");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Text text)) {
            return false;
        }
        return content.equals(text.content) && styles.equals(text.styles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, styles);
    }

    public static Builder builder(String content) {
        return new Builder(content);
    }

    /**
     * Text builder class, used for specifying text formatting.
     */
    public static final class Builder {
        private final String content;
        private final EnumSet<Style> styles = EnumSet.noneOf(Style.class);

        private Builder(String content) {
            this.content = content;
        }

        public Builder bold() {
            styles.add(Style.BOLD);
            return this;
        }

        public Builder italic() {
            styles.add(Style.ITALIC);
            return this;
        }

        public Builder strikethrough() {
            styles.add(Style.STRIKE);
            return this;
        }

        public Builder code() {
            styles.add(Style.CODE);
            return this;
        }

        private void check() {
            if (content == null || content.isBlank()) {
                throw new IllegalArgumentException("Text content should not be empty");
            }

            if (styles.contains(Style.CODE) && styles.size() > 1) {
                throw new IllegalArgumentException("Inline can't be combined with other styles");
            }
        }

        /**
         * Build method, returns formatted text string.
         */
        public Text build() {
            check();
            return new Text(this);
        }
    }
}
