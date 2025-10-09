package ru.nsu.ekovalenko4.expressions;

/**
 * Custom expression-related exception class.
 * Thrown when expression handling fails.
 */
public class ExpressionException extends RuntimeException {
    public ExpressionException(String message) {
        super(message);
    }
}
