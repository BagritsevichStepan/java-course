package expression;

import java.math.BigDecimal;

public class Pow extends BinaryOperation {
    public Pow(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.POW);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return (int) Math.pow(a, b);
    }

    @Override
    protected BigDecimal makeDecimalOperation(BigDecimal a, BigDecimal b) {
        return a.pow(b.intValue());
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
