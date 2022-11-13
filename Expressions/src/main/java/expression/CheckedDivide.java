package expression;

import expression.exceptions.DivideOverflowException;
import expression.exceptions.DivisionByZeroException;

import java.math.BigDecimal;

public class CheckedDivide extends BinaryOperation {
    public CheckedDivide(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.DIVIDE);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return a / b;
    }

    @Override
    protected int makeCheckedIntOperation(int a, int b) {
        if (b == 0) {
            throw new DivisionByZeroException(getErrorMessage(a, b));
        }
        if (b == -1 && a == Integer.MIN_VALUE) {
            throw new DivideOverflowException(getErrorMessage(a, b));
        }
        return a / b;
    }

    @Override
    protected BigDecimal makeDecimalOperation(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }

    @Override
    protected BigDecimal makeCheckedDecimalOperation(BigDecimal a, BigDecimal b) {
        if (b.equals(BigDecimal.ZERO)) {
            throw new DivisionByZeroException(getErrorMessage(a, b));
        }
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
