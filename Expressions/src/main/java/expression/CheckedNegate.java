package expression;

import expression.exceptions.NegateOverflowException;

import java.math.BigDecimal;

public class CheckedNegate extends UnaryOperation {
    public CheckedNegate(ExpressionPriority expression) {
        super(expression, Operator.NEGATE);
    }

    @Override
    protected int makeIntOperation(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new NegateOverflowException(getErrorMessage(a));
        }
        return -a;
    }

    @Override
    public BigDecimal makeDecimalOperation(BigDecimal a) {
        return a.negate();
    }
}
