package ru.nsu.ekovalenko4.markdown;

import java.util.Objects;

/**
 * Represents a Markdown image with alt text, source, and optional title.
 */
public final class Image extends Element {
    private final String alt;
    private final String src;
    private final String title;

    public Image(String alt, String src, String title) {
        this.alt = alt == null ? "" : alt;
        if (src == null || src.isBlank()) {
            throw new IllegalArgumentException("Image source cannot be null or empty");
        }
        this.src = src;
        this.title = title;
    }

    @Override
    public String toMarkdown() {
        if (title == null || title.isEmpty()) {
            return "![" + alt + "](" + src + ")";
        } else {
            return "![" + alt + "](" + src + " \"" + title + "\")";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Image other)) {
            return false;
        }
        return Objects.equals(alt, other.alt)
                && Objects.equals(src, other.src)
                && Objects.equals(title, other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alt, src, title);
    }

    public String alt() {
        return alt;
    }

    public String src() {
        return src;
    }

    public String title() {
        return title;
    }

}
