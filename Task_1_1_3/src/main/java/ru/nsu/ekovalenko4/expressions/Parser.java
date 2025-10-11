package ru.nsu.ekovalenko4.expressions;

/**
 * A simple parser for mathematical expressions.
 * All non-trivial expressions must be wrapped in parentheses.
 */
public class Parser {
    static Expression parse(String s) {

        if (!s.isEmpty()) {

            boolean isNumber = true;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!(c == '-' && i == 0) && !Character.isDigit(c)) {
                    isNumber = false;
                    break;
                }
            }
            if (isNumber) {
                return new Number(Double.parseDouble(s));
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

            if (s.charAt(0) == '(') {
                String inside = s.substring(1, s.length() - 1);
                int depth = 0;
                for (int i = 0; i < inside.length(); i++) {
                    char c = inside.charAt(i);
                    if (c == '(') {
                        depth++;
                    }
                    if (c == ')') {
                        depth--;
                    }
                    if (depth == 0 && (c == '+' || c == '-' || c == '*' || c == '/')) {
                        String left = inside.substring(0, i);
                        String right = inside.substring(i + 1);
                        Expression l = parse(left);
                        Expression r = parse(right);
                        return switch (c) {
                            case '+' -> new Add(l, r);
                            case '-' -> new Sub(l, r);
                            case '*' -> new Mul(l, r);
                            case '/' -> new Div(l, r);
                            default -> throw new ParseException("Invalid operator: " + c);
                        };
                    }
                }
            }
        }

        throw new ParseException("Invalid expression, cannot parse: " + s);
    }
}
