package com.daria.learn.week1;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/*
Check whether a string represents a mathematical expression and evaluate it.

In addition to the basic solution introduced during lessons, which supported only single-digit, positive numbers, operations +, -, *, / and brackets, the solution should also support:

Floating point numbers: anything which consists of at least 1 digit and at most 1 dot (‘.’). (Negative numbers can be achieved by unary -.)
Unary - even if not followed by a number.
Power operation (‘^’). Note that we consider the power operation to be of a higher priority than a unary minus.
Unary functions abs (absolute value) and sqrt (square root).
Printing string  "Invalid mathematical expression.", in the following situations:
if the input is not a valid mathematical expression, e.g. 9*+),
or if the solution is not a real number, e.g. sqrt(-1),
or if the left operand (base) of the power operation is negative,
or if the solution does not exist, e.g. 1/0.
Arbitrary number of spaces, e.g. " 2 +   (3-2)" is a valid input.

 */

public class ExpressionEvaluator {

    private enum EXPRESSION_STATE  {
        TERM_EXPECTED,
        EXPRESSION_EXPECTED
    }

    private int position;
    private int currChar;
    private String expression;
    private final Map<String, Function<Double, Double>> supportedUnary = new HashMap<>();
    private EXPRESSION_STATE state;

    public ExpressionEvaluator(String expr) {
        expression = expr;
        position = -1;

        supportedUnary.put("abs", Math::abs);
        supportedUnary.put("sqrt", (value) -> Math.sqrt(use_validated((p) -> p > -1, value)));

    }

    private void next() {
        if (++position >= expression.length())
            currChar = -1;
        else {
            if (expression.charAt(position) == ' ')
                next();
            else
                currChar = expression.charAt(position);
        }
    }

    private boolean should_apply(char c) {
        if (currChar == c) {
            next();
            return true;
        }
        return false;
    }

    public double eval() {
        next();
        return eval_expr();
    }

    private double eval_expr() {
        double result = eval_term();
        while (true) {
            if (should_apply('+')) {
                result += eval_term();
            } else if (should_apply('-')) {
                result -= eval_term();
            }
            else return result;
        }
    }

    private double eval_term() {

        double result = eval_factor();
        while(true) {
            if (should_apply('*')) {
                result *= eval_factor();
            } else if (should_apply('/')) {
                result /= use_validated((value) -> value != 0, eval_factor());
            }
            else return result;
        }
    }

    private double use_validated(Predicate<Double> condition, double value) {
        if (!condition.test(value))
            throw new InvalidExpressionInput();
        return value;
    }

    private double eval_factor() {
        double result = 0.0;
        if (should_apply('(')) {
            result = use_validated(exp -> should_apply(')'), eval_expr()); //???
//            should_apply(')'); //!!!
        } else if ((currChar >= '0' && currChar <= '9') || currChar == '.') {
            result = evaluate_number();
        } else if (Character.isLetter(currChar)) {
            result = evaluate_unary_literal();
        }
        else if (currChar == '-') {
            next();
            result = -1 * eval_factor();
        } else {
            throw new InvalidExpressionInput();
        }
        if (should_apply('^')) {
            result = Math.pow(use_validated(value -> value >= 0, result), eval_factor());
        }
        return result;
    }

    private double evaluate_unary_literal() {
        int start = position;
        while (Character.isLetter(currChar)) {
            next();
        }
        String operation = expression.substring(start, position).toLowerCase();

        if (!supportedUnary.containsKey(operation))
            throw new InvalidExpressionInput("invalid literal operation");

        if (!should_apply('('))
            throw new InvalidExpressionInput("Operation " + operation + " should be enclosed in parenthesis");

        double result = use_validated(exp -> should_apply(')'), supportedUnary.get(operation).apply(eval_expr()));
        return result;
    }

    private double evaluate_number() {
        int start = position;
        boolean dotIsMet = false;
        while ((currChar >= '0' && currChar <= '9') || currChar == '.') {
            if (currChar == '.' && dotIsMet) //???
                throw new IllegalArgumentException("Invalid mathematical expression.");
            if (currChar == '.')
                dotIsMet = true;
            next();
        }
        return Double.valueOf(expression.substring(start, position));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        System.out.println(new ExpressionEvaluator(expression).eval());
        scanner.close();
    }

    public class InvalidExpressionInput extends RuntimeException {
        public InvalidExpressionInput(String message){
            super(message);
        }

        public InvalidExpressionInput(){super();}
    }
}