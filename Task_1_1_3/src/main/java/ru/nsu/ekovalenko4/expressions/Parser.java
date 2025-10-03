package ru.nsu.ekovalenko4.expressions;

public class Parser {
    static Expression parse(String s) {

        boolean isNumber = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(c == '-' && i == 0) && !Character.isDigit(c)) {
                isNumber = false;
                break;
            }
        }
        if (isNumber) {
            return new Number(Integer.parseInt(s));
        }

        boolean isVariable = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isLetter(c)) {
                isVariable = false;
                break;
            }
        }
        if (isVariable) {
            return new Variable(s);
        }

        if (s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')') {
            String inside = s.substring(1, s.length() - 1);
            int depth = 0;
            for (int i = 0; i < inside.length(); i++) {
                char c = inside.charAt(i);
                if (c == '(') depth++;
                else if (c == ')') depth--;
                else if ((c == '+' || c == '-' || c == '*' || c == '/') && depth == 0) {
                    String left = inside.substring(0, i);
                    String right = inside.substring(i + 1);
                    Expression l = parse(left);
                    Expression r = parse(right);
                    switch (c) {
                        case '+': return new Add(l, r);
                        case '-': return new Sub(l, r);
                        case '*': return new Mul(l, r);
                        case '/': return new Div(l, r);
                    }
                }
            }
        }

        throw new RuntimeException("Не удалось разобрать выражение: " + s);
    }
}
