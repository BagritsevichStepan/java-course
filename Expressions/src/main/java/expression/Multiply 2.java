package expression;

import java.math.BigDecimal;

public class Multiply extends BinaryOperation {
    public Multiply(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.MULTIPLY);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return a * b;
    }

    @Override
    protected BigDecimal makeDecimalOperation(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
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
        return true;
    }
}
