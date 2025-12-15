package ru.nsu.ekovalenko4.markdown;

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
