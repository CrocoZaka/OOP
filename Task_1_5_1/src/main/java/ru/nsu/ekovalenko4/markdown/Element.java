package ru.nsu.ekovalenko4.markdown;

/**
 * Base abstract class for all Markdown elements.
 * Provides interface for converting element to Markdown.
 */
public abstract class Element {

    public abstract String toMarkdown();

    @Override
    public String toString() {
        return toMarkdown();
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();
}
