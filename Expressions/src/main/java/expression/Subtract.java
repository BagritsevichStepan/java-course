package expression;

import java.math.BigDecimal;

public class Subtract extends BinaryOperation {
    public Subtract(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.SUBTRACT);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return a - b;
    }

    @Override
    protected BigDecimal makeDecimalOperation(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    @Override
    public boolean orderIsImportant() {
        return true;
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
