package ru.nsu.ekovalenko4.expressions;

/**
 * Represents a variable in an expression.
 */
class Variable extends Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Expression derivative(String var) {
        if (name.equals(var)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    @Override
    public double eval(String values) {
        String[] parts = values.split(";");
        for (String s : parts) {
            String part = s.trim();
            if (part.isEmpty()) continue;
            String[] eq = part.split("=");
            String varName = eq[0].trim();
            String varValue = eq[1].trim();
            if (varName.equals(name)) {
                return Double.parseDouble(varValue);
            }
        }
        throw new IllegalArgumentException("Variable " + name + " is not defined");
    }
}
