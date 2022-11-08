package expression;

import expression.exceptions.PowInvalidArgumentsException;
import expression.exceptions.PowOverflowException;

import java.math.BigDecimal;

public class CheckedPow extends BinaryOperation {
    public CheckedPow(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.POW);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        if ((a == 0 && b == 0) || b < 0) {
            throw new PowInvalidArgumentsException(getErrorMessage(a, b));
        }
        return calcPow(a, b);
    }

    private int calcPow(int a, int b) {
        int result = 1, evenResult = a, pow = b;
        while (pow > 0) {
            if (pow % 2 == 1) {
                if (CheckedMultiply.checkMultiplyOverflow(result, evenResult)) {
                    throw new PowOverflowException(getErrorMessage(a, b));
                }

                result *= evenResult;
                pow--;
            } else {
                if (CheckedMultiply.checkMultiplyOverflow(evenResult, evenResult)) {
                    throw new PowOverflowException(getErrorMessage(a, b));
                }

                evenResult *= evenResult;
                pow /= 2;
            }
        }
        return result;
    }

    @Override
    protected BigDecimal makeDecimalOperation(BigDecimal a, BigDecimal b) {
        if ((a.equals(BigDecimal.ZERO) && b.equals(BigDecimal.ZERO)) ||
                b.compareTo(BigDecimal.ZERO) < 0 ||
                b.compareTo(new BigDecimal(999999999)) > 0
        ) {
            throw new PowInvalidArgumentsException(getErrorMessage(a, b));
        }
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
