import expression.ExpressionPriority;
import expression.exceptions.ExpressionException;
import expression.exceptions.ParsingException;
import expression.parser.ExpressionParser;

import java.math.BigDecimal;

public class Example {
    private static void outputExpression(ExpressionPriority expression) {
        System.out.println("Expression: " + expression);
        System.out.println("Expression with a minimum number of brackets: " + expression.toMiniString());
    }

    private static void expressionExample(ExpressionPriority expression, Object x, boolean checkedMode) {
        outputExpression(expression);
        System.out.print("Expression result with x=" + x + ": ");
        if (x instanceof Integer) {
            System.out.println(expression.evaluate((Integer) x, checkedMode));
        }
        if (x instanceof BigDecimal) {
            System.out.println(expression.evaluate((BigDecimal) x, checkedMode));
        }
    }

    private static void expressionExample(ExpressionPriority expression, Object x,
                                          Object y, Object z, boolean checkedMode) {
        outputExpression(expression);
        System.out.print("Expression result with x=" + x + ", y=" + y + ", z=" + z + ": ");
        if (x instanceof Integer) {
            System.out.println(expression.evaluate((Integer) x, (Integer) y, (Integer) z, checkedMode));
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("3 parameters are required");
        }

        // Expression -x*l08+(4*-(6)) with int x
        // CheckedMode == true
        try {
            expressionExample(
                    new ExpressionParser().parse("-x*l08+(4*-(6))"),
                    Integer.valueOf(args[0]),
                    true
            );
            System.out.println();
        } catch (ParsingException e) {
            System.err.println("Parsing exception: " + e.getMessage());
        } catch (ExpressionException e) {
            System.err.println("Expression exception:" + e.getMessage());
        } catch (NumberFormatException ignored) {}

        // Expression -(3.86)+(4.5*x) with BigDecimal x
        // CheckedMode == false
        try {
            expressionExample(
                    new ExpressionParser().parse("-(3.86)+(4.5*x)"),
                    new BigDecimal(args[0]),
                    false
            );
            System.out.println();
        } catch (ParsingException e) {
            System.err.println("Parsing exception: " + e.getMessage());
        } catch (ExpressionException e) {
            System.err.println("Expression exception:" + e.getMessage());
        }

        // Expression (-3*(x))+y*z+t04 with int x, int y, int z
        // CheckedMode == true
        try {
            expressionExample(
                    new ExpressionParser().parse("(-3*(x))+y*z+t04"),
                    Integer.valueOf(args[0]),
                    Integer.valueOf(args[1]),
                    Integer.valueOf(args[2]),
                    true
            );
        } catch (ParsingException e) {
            System.err.println("Parsing exception: " + e.getMessage());
        } catch (ExpressionException e) {
            System.err.println("Expression exception:" + e.getMessage());
        } catch (NumberFormatException ignored) {}
    }
}
