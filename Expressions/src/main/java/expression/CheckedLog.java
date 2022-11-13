package expression;

import expression.exceptions.LogInvalidArgumentsException;

import java.math.BigDecimal;

public class CheckedLog extends BinaryOperation {
    public CheckedLog(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.LOG);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return (int) (Math.log(a) / Math.log(b));
    }

    @Override
    protected int makeCheckedIntOperation(int a, int b) {
        if (!(a > 0 && b > 1)) {
            throw new LogInvalidArgumentsException(getErrorMessage(a, b));
        }
        int result = calcLog(a, b);
        if (result <= 0) {
            throw new LogInvalidArgumentsException(getErrorMessage(a, b));
        }
        return result;
    }

    @Override
    protected BigDecimal makeDecimalOperation(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("BigDecimal operation - unsupported for log operation");
    }

    private static int calcLog(int a, int b) {
        int result = -1;
        while (a > 0) {
            a /= b;
            result++;
        }
        return result;
    }

    @Override
    protected String getErrorMessage(int a, int b) {
        return "Log_(" + b + ")(" + a + ")";
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
