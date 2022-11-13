package expression;

import expression.exceptions.AddOverflowException;

import java.math.BigDecimal;

public class CheckedAdd extends BinaryOperation {
    public CheckedAdd(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.ADD);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return a + b;
    }

    @Override
    protected int makeCheckedIntOperation(int a, int b) {
        if ((b > 0 && a > Integer.MAX_VALUE - b) || (b < 0 && a < Integer.MIN_VALUE - b)) {
            throw new AddOverflowException(getErrorMessage(a, b));
        }
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
