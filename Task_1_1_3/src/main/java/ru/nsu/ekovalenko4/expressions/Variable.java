package ru.nsu.ekovalenko4.expressions;

class Variable extends Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String print() {
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
    public int eval(String values) {
        String[] parts = values.split(";");
        for (String part : parts) {
            if (part.isEmpty()) continue;
            String[] eq = part.split("=");
            String varName = eq[0].trim();
            String varValue = eq[1].trim();
            if (varName.equals(name)) {
                return Integer.parseInt(varValue);
            }
        }
        throw new IllegalArgumentException("Variable " + name + " is not defined");
    }
}
