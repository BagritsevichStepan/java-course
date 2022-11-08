package expression;

import java.math.BigDecimal;

public class Negate extends UnaryOperation {
    public Negate(ExpressionPriority expression) {
        super(expression, Operator.NEGATE);
    }

    @Override
    protected int makeIntOperation(int a) {
        return -a;
    }

    @Override
    public BigDecimal makeDecimalOperation(BigDecimal a) {
        return a.negate();
    }
}
