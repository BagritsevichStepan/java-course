package expression;

import java.math.BigDecimal;

public class Log extends BinaryOperation {
    public Log(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.LOG);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return (int) (Math.log(a) / Math.log(b));
    }
    @Override
    protected BigDecimal makeDecimalOperation(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("BigDecimal operation - unsupported for log operation");
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
