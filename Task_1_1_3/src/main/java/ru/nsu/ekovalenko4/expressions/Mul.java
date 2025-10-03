package ru.nsu.ekovalenko4.expressions;

class Mul extends BinaryOperation {
    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }

    @Override
    public Expression derivative(String var) {
        return new Add(
                new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var))
        );
    }

    @Override
    public double eval(String values) {
        return left.eval(values) * right.eval(values);
    }
}