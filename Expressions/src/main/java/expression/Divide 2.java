package expression;

import java.math.BigDecimal;

public class Divide extends BinaryOperation {
    public Divide(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.DIVIDE);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return a / b;
    }

    @Override
    protected BigDecimal makeDecimalOperation(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }

    @Override
    public boolean orderIsImportant() {
        return true;
    }

    @Override
    public boolean canBeDecimalResult() {
        return true;
    }

    @Override
    public boolean canChangeDecimalRounding() {
        return true;
    }
}
