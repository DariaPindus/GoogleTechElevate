package com.daria.learn.week1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

//(3 +2) * 4
public class ExpressionEvaluatorNew {

    private class Operator implements Comparable<Operator> {
        private final int priority;
        private final BiFunction<Double, Double, Double> executor;

        private Operator(int priority, BiFunction<Double, Double, Double> executor) {
            this.priority = priority;
            this.executor = executor;
        }

        @Override
        public int compareTo(Operator o) {
            return priority - o.priority;
        }
    }

    private int position;
    private int currChar;
    private String expression;
    private final Map<String, Operator> operators = new HashMap<>();


    public ExpressionEvaluatorNew(String expr) {
        expression = expr;
        position = -1;
        initMap();
    }


    private void initMap() {
        operators.put("+", new Operator(1, (a, b) -> a + b));
        operators.put("-", new Operator(1, (a, b) -> a - b));
        operators.put("*", new Operator(2, (a, b) -> a * b));
        operators.put("/", new Operator(2, (a, b) -> a / b));
        operators.put("^", new Operator(3, (a, b) -> Math.pow(a, b)));
        operators.put("abs", new Operator(1, (a, b) -> Math.abs(a)));
    }

    private void next() {
        currChar = ++position < expression.length() ? expression.charAt(position) : -1;
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

    private double eval_term() {
        double result = eval_factor();
        while(true) {
            if (should_apply('*')) {
                result *= eval_factor();
            } else if (should_apply('/')) {
                result /= eval_factor();
            }
            else return result;
        }
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

    private double eval_factor() {
        double result = 0.0;
        if (should_apply('(')) {
            result = eval_expr();
            should_apply(')');
        } else if (currChar >= '0' && currChar <= '9') {
            next();
            result = Double.valueOf(expression.substring(position - 1, position));
        }
        return result;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        System.out.println(new ExpressionEvaluator(expression).eval());
        scanner.close();
    }
}
