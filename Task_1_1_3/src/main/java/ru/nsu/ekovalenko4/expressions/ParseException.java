package ru.nsu.ekovalenko4.expressions;

/**
 * Custom parser-related exception class.
 * Thrown when expression parsing fails.
 */
public class ParseException extends RuntimeException {
    public ParseException(String message) {
        super(message);
    }
}
