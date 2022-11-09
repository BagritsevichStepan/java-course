package expression;

import java.math.BigDecimal;

public abstract class ShiftOperation extends BinaryOperation {
    protected ShiftOperation(ExpressionPriority left, ExpressionPriority right, Operator operation) {
        super(left, right, operation);
    }

    @Override
    protected BigDecimal makeDecimalOperation(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("BigDecimal operation - unsupported for shift operations");
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
