package ru.nsu.ekovalenko4.markdown;

import java.util.Objects;

/**
 * Represents a fenced code block in Markdown with optional language.
 */
public final class CodeBlock extends Element {
    private final String language;
    private final String code;

    public CodeBlock(String language, String code) {
        this.language = language == null ? "" : language;
        this.code = code == null ? "" : code;
    }

    @Override
    public String toMarkdown() {
        String fence = "```";
        return fence + language + "\n" + code + "\n" + fence;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CodeBlock other)) {
            return false;
        }
        return Objects.equals(language, other.language) && Objects.equals(code, other.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, code);
    }

    public String language() {
        return language;
    }

    public String code() {
        return code;
    }

}
