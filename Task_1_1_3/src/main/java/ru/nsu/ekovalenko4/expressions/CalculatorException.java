package ru.nsu.ekovalenko4.expressions;

/**
 * Base class for custom exceptions.
 * This allows catching all calculator-related errors with a single catch (CalculatorException e)
 * code block.
 */
public class CalculatorException extends RuntimeException {
    public CalculatorException(String message) {
        super(message);
    }
}