package test;

import com.daria.learn.week1.ExpressionEvaluator;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionEvaluatorTest {
    public static final double EPSILON = 0.001;
    
    @Test
    public void testDifferentExpressions() {
        String simpleExpression = "3*(2+1)";
        assertEquals(9, new ExpressionEvaluator(simpleExpression).eval(), EPSILON);

        String simpleWithWhitespaces = "(2 + 3 *7)- 4";
        assertEquals(19, new ExpressionEvaluator(simpleWithWhitespaces).eval(), EPSILON);

        String simpleDotMultiDigit = "2.345 - 0.222*1.1";
        assertEquals((2.345 - 0.222*1.1), new ExpressionEvaluator(simpleDotMultiDigit).eval(), EPSILON);

        String unaryExpression = "-2 * 5-(1+3)";
        assertEquals(-2*5-(1+3), new ExpressionEvaluator(unaryExpression).eval(), EPSILON);

        String unaryExpr = "-3*2";
        assertEquals(-6, new ExpressionEvaluator(unaryExpr).eval(), 0.0001);

        String powerExpr1 = "7^2 - 8*2.1";
        assertEquals(Math.pow(7, 2) - 8*2.1, new ExpressionEvaluator(powerExpr1).eval(), EPSILON);

        String powerExpr2 = "2 -3^2 + 10.1";
        assertEquals(3.1, new ExpressionEvaluator(powerExpr2).eval(), EPSILON);

        String powerExpr3 = "3* (-2)^3 - 1";
        assertEquals(3*Math.pow(-2, 3)-1, new ExpressionEvaluator(powerExpr3).eval(), EPSILON);

        String absExpression = "abs(-3)";
        assertEquals(3, new ExpressionEvaluator(absExpression).eval(), EPSILON);

        String negativeAbs = "-abs(-3)";
        assertEquals(-Math.abs(-3), new ExpressionEvaluator(negativeAbs).eval(), EPSILON);

        String absComplexExpr = "-5 + 3 *(2 -abs(-3))";
        double expectedAbsComplexExpr = -5.0 + 3*(2-Math.abs(-3));
        assertEquals(expectedAbsComplexExpr, new ExpressionEvaluator(absComplexExpr).eval(), EPSILON);

        String simpleSqrtExpr= "2.5*sqrt(16)";
        assertEquals(2.5 * Math.sqrt(16), new ExpressionEvaluator(simpleSqrtExpr).eval(), EPSILON);

        String sqrtExpr = "sqrt(16)*(-5.5)";
        assertEquals(Math.sqrt(16)*(-5.5), new ExpressionEvaluator(sqrtExpr).eval(), EPSILON);

        //        ???
//        String sqrtExpr2 = "sqrt(16)*-5.5";
//        assertEquals(Math.sqrt(16)*-5.5, new ExpressionEvaluator(sqrtExpr2).eval(), EPSILON);
    }

    @Test
    public void testExpressionExceptions() {

        String simpleWrongExpr = "9*+1-22";
        assertThrows(ExpressionEvaluator.InvalidExpressionInput.class, () -> new ExpressionEvaluator(simpleWrongExpr).eval());

        String invalidNumberOfParenthesis = "3*(2 + 1";
        assertThrows(ExpressionEvaluator.InvalidExpressionInput.class, () -> new ExpressionEvaluator(invalidNumberOfParenthesis).eval());
    }

    @Test
    public void googleTests() {
        String expression = "abs(2.2-(3*2))";
        assertEquals("3.80", toOutputResult(new ExpressionEvaluator(expression).eval()));

        String expression2 = "-3^4";
        assertEquals("-81.00", toOutputResult(new ExpressionEvaluator(expression2).eval()));

        String expression3 = "1+2*3^4";
        assertEquals("163.00", toOutputResult(new ExpressionEvaluator(expression3).eval()));

        String expression4 = "(-3)^4";
        assertThrows(ExpressionEvaluator.InvalidExpressionInput.class, () -> new ExpressionEvaluator(expression4).eval());

        String expression5 = "12345677*-(-(-(((98765431.)))))";
        assertEquals("-1219326109891787.00", toOutputResult(new ExpressionEvaluator(expression5).eval()));

        String expression6 = "(abs( 12 ^ 3- abs(-12.55) * abs(4*(12  -sqrt(-3.6/-.9)))))^3 +- -(-1842767890.)";
        assertEquals("3286.00", toOutputResult(new ExpressionEvaluator(expression6).eval()));
    }

    @Test
    public void additionGoogle() {
        String exp = "-2^-2";
        assertEquals("-0.25", toOutputResult(new ExpressionEvaluator(exp).eval()));

        String exp2 = "2^-3";
        assertEquals("0.13", toOutputResult(new ExpressionEvaluator(exp2).eval()));
//
        String exp3 = "-4--5";
        assertEquals("1.00", toOutputResult(new ExpressionEvaluator(exp3).eval()));
    }

    @Test
    public void testInvalid() {
//        String[] inputs = new String[]{"+0", "+1", "+(123)", "1/0", "(1+2)*3(4+5)+", "(2+3))", "(-3)^4"};
        String[] inputs = new String[]{"(1+2)*3(4+5)+", "(2+3))", "(-3)^4"};
        for (String s : inputs) {
            System.out.println("Test " + s);
            assertThrows(ExpressionEvaluator.InvalidExpressionInput.class, () -> new ExpressionEvaluator(s).eval());
        }
    }

    private String toOutputResult(double res) {
        return String.format("%.2f", res).replace(",", ".");
    }
}
