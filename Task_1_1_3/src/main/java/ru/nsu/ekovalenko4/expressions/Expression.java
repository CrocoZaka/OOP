package ru.nsu.ekovalenko4.expressions;

abstract class Expression {
    public abstract String print();
    public abstract Expression derivative(String var);
    public abstract int eval(String values);
}
