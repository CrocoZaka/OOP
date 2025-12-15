package ru.nsu.ekovalenko4.markdown;

import java.util.Objects;

/**
 * Represents a Markdown link with text, URL, and optional title.
 */
public final class Link extends Element {
    private final Text content;
    private final String url;
    private final String title;

    /**
     * Constructs Link with specified Text content and url.
     */
    public Link(Text content, String url) {
        this(content, url, null);
    }

    /**
     * Constructs Link with specified Text content, url and title string.
     */
    public Link(Text content, String url, String title) {
        this.content = content;
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        this.url = url;
        this.title = title;
    }

    @Override
    public String toMarkdown() {
        String t = content.toMarkdown();
        if (title == null || title.isEmpty()) {
            return "[" + t + "](" + url + ")";
        } else {
            return "[" + t + "](" + url + " \"" + title + "\")";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Link other)) {
            return false;
        }
        return Objects.equals(content, other.content) && Objects.equals(url, other.url)
            && Objects.equals(title, other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, url, title);
    }

    public Text text() {
        return content;
    }

    public String url() {
        return url;
    }

    public String title() {
        return title;
    }
}
