package expression;

import expression.exceptions.SubtractOverflowException;

import java.math.BigDecimal;

public class CheckedSubtract extends BinaryOperation {
    public CheckedSubtract(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.SUBTRACT);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return a - b;
    }

    @Override
    protected int makeCheckedIntOperation(int a, int b) {
        if ((b > 0 && a < Integer.MIN_VALUE + b) || (b < 0 && a > Integer.MAX_VALUE + b)) {
            throw new SubtractOverflowException(getErrorMessage(a, b));
        }
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
