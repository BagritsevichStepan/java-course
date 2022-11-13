package expression;

import expression.exceptions.AbsOverflowException;

import java.math.BigDecimal;

public class CheckedAbs extends UnaryOperation {
    public CheckedAbs(ExpressionPriority expression) {
        super(expression, Operator.ABS);
    }

    @Override
    protected int makeIntOperation(int a) {
        return Math.abs(a);
    }

    @Override
    protected int makeCheckedIntOperation(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new AbsOverflowException(getErrorMessage(a));
        }
        return (a >= 0) ? a : -a;
    }

    @Override
    public BigDecimal makeDecimalOperation(BigDecimal a) {
        return a.abs();
    }
}
