package expression;

import java.math.BigDecimal;

public class NumberOfTrailingZeros extends UnaryOperation {
    public NumberOfTrailingZeros(ExpressionPriority expression) {
        super(expression, Operator.T0);
    }

    @Override
    protected int makeIntOperation(int a) {
        return Integer.numberOfTrailingZeros(a);
    }

    @Override
    public BigDecimal makeDecimalOperation(BigDecimal a) {
        throw new UnsupportedOperationException("BigDecimal operation - unsupported for " +
                Operator.getOperatorSign(Operator.T0) + " operation");
    }
}
