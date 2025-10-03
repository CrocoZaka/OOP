package ru.nsu.ekovalenko4.expressions;

/**
 * Abstract base class for all mathematical expressions.
 * Provides methods for printing, differentiation, and evaluation.
 */
abstract class Expression {
    public abstract Expression derivative(String var);
    public abstract double eval(String values);
}
