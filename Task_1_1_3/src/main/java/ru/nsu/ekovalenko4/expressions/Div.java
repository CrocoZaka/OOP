package ru.nsu.ekovalenko4.expressions;

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
    public double eval(String values) {
        return left.eval(values) / right.eval(values);
    }
}