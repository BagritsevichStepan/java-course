package expression;

import expression.exceptions.MultiplyOverflowException;

import java.math.BigDecimal;

public class CheckedMultiply extends BinaryOperation {
    public CheckedMultiply(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.MULTIPLY);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        if (checkMultiplyOverflow(a, b)) {
            throw new MultiplyOverflowException(getErrorMessage(a, b));
        }
        return a * b;
    }

    public static boolean checkMultiplyOverflow(int a, int b) {
        // ADD: a == Integer.MIN_VALUE && b == -1
        // ADD: b == Integer.MIN_VALUE && a == -1
        return a != 0 && b != 0 && (a * b) / b != a;
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
