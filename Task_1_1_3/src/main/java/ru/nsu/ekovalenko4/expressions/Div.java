package ru.nsu.ekovalenko4.expressions;

import java.util.Map;

class Div extends BinaryOperation {
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "/" + right.toString() + ")";
    }

    @Override
    public Expression derivative(String var) {
        return new Div(
                new Sub(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var))),
                new Mul(right, right)
        );
    }

    @Override
    public double eval(Map<String, Double> vars) {
        double denominator = right.eval(vars);
        if (denominator == 0) {
            throw new ExpressionException("Division by zero");
        }
        return left.eval(vars) / denominator;
    }
}