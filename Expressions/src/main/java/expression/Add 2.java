package expression;

import java.math.BigDecimal;

public class Add extends BinaryOperation {
    public Add(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.ADD);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return a + b;
    }

    @Override
    protected BigDecimal makeDecimalOperation(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    @Override
    public boolean orderIsImportant() {
        return false;
    }
    @Override
    public boolean canBeDecimalResult() {
        return false;
    }

    @Override
    public boolean canChangeDecimalRounding() {
        return false;
    }
}
