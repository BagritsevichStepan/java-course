package expression;

import java.math.BigDecimal;

public class NumberOfLeadingZeros extends UnaryOperation {
    public NumberOfLeadingZeros(ExpressionPriority expression) {
        super(expression, Operator.L0);
    }

    @Override
    protected int makeIntOperation(int a) {
        return Integer.numberOfLeadingZeros(a);
    }

    @Override
    public BigDecimal makeDecimalOperation(BigDecimal a) {
        throw new UnsupportedOperationException("BigDecimal operation - unsupported for " +
                Operator.getOperatorSign(Operator.L0) + " operation");
    }
}
