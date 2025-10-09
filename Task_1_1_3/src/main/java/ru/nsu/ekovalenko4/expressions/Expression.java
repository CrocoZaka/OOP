package ru.nsu.ekovalenko4.expressions;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract base class for all mathematical expressions.
 * Provides methods for differentiation and evaluation.
 */
abstract class Expression {
    public abstract Expression derivative(String var);

    public double eval(String s) {
        Map<String, Double> vars = parseValues(s);
        return eval(vars);
    };

    protected abstract double eval(Map<String, Double> vars);

    private Map<String, Double> parseValues(String s) {
        Map<String, Double> vars = new HashMap<>();
        String[] parts = s.split(";");
        for (String part : parts) {
            part = part.trim();
            if (!part.isEmpty()) {
                String[] eq = part.split("=");
                if (eq.length == 2) {
                    vars.put(eq[0].trim(), Double.parseDouble(eq[1].trim()));
                };
            }
        }
        return vars;
    }
}
