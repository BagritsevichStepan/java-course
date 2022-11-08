package expression;

import expression.exceptions.AbsOverflowException;

import java.math.BigDecimal;

public class Abs extends UnaryOperation {
    public Abs(ExpressionPriority expression) {
        super(expression, Operator.ABS);
    }

    @Override
    protected int makeIntOperation(int a) {
        return Math.abs(a);
    }

    @Override
    public BigDecimal makeDecimalOperation(BigDecimal a) {
        return a.abs();
    }
}
