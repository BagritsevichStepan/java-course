import expression.*;
import expression.exceptions.ExpressionException;
import expression.exceptions.ParsingException;
import expression.parser.ExpressionParser;
import expression.parser.Parser;

import java.math.BigDecimal;

public class Example {
    class ExampleExpression<T> {
        private final ExpressionPriority expression;
        private final T x;
        private final T y;
        private final T z;

        ExampleExpression(ExpressionPriority expression, T x, T y, T z) {
            this.expression = expression;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        ExampleExpression(ExpressionPriority expression, T x) {
            this(expression, x, null, null);
        }

        @Override
        public String toString() {
            return "Expression: " + expression + System.lineSeparator() +
                    "Expression with a minimum number of brackets: " + expression.toMiniString() + System.lineSeparator() +
                    System.lineSeparator() +
                    "Expression result with x = " + x + ": " + (
                            y == null ?
                            expression.evaluate(x) :
                            expression.evaluate(x, y, z)
            );
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("3 parameters are required");
        }

        try {
            Expression expression = new ExpressionParser().parse("-x*l08+(4*-(6))");
            System.out.println("Expression: " + expression);
            System.out.println("Expression with a minimum number of brackets: " +
                    expression.toMiniString());
            System.out.println();
            try {
                System.out.println("Expression result with x = " + args[0] + ": " +
                                expression.evaluate(Integer.parseInt(args[0])));
            } catch (ExpressionException e) {
                System.err.println("Expression exception:" + e.getMessage());
            }
        } catch (ParsingException e) {
            System.err.println("Parsing exception: " + e.getMessage());
        }

        try {
            BigDecimalExpression expression = new ExpressionParser().parse("-(3.86)+(4.5*x)");
            System.out.println();
            System.out.println("BigDecimal expression: " + expression);
            System.out.println("BigDecimal expression with a minimum number of brackets: " +
                    expression.toMiniString());
            try {
                System.out.println("Expression result with x = " + args[0] + ": " +
                        expression.evaluate(new BigDecimal(args[0])));
            } catch (ExpressionException e) {
                System.err.println(e.getMessage());
            }
        } catch (ParsingException e) {
            System.err.println(e.getMessage());
        }

        try {
            TripleExpression expression = new ExpressionParser().parse("(-3*(x))+y*z+t04");
            System.out.println();
            System.out.println("Triple expression: " + expression);
            System.out.println("Triple expression with a minimum number of brackets: " +
                    expression.toMiniString());
            try {
                System.out.println(
                        "Expression result with x = " + args[0] +
                                ", y = " + args[1] +
                                ", z = " + args[2] +
                                ": " + expression.evaluate(Integer.parseInt(args[0]),
                                Integer.parseInt(args[1]),
                                Integer.parseInt(args[2])
                        )
                );
            } catch (ExpressionException e) {
                System.err.println(e.getMessage());
            }
        } catch (ParsingException e) {
            System.err.println(e.getMessage());
        }
    }
}
