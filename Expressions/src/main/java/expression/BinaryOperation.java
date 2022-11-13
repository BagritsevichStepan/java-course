package expression;

import java.math.BigDecimal;

public abstract class BinaryOperation extends Operation {
    protected final ExpressionPriority left;
    protected final ExpressionPriority right;

    protected BinaryOperation(ExpressionPriority left, ExpressionPriority right, Operator operator) {
        super(operator);
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate(int x, boolean checkedMode) {
        final int leftValue = left.evaluate(x, checkedMode);
        final int rightValue = right.evaluate(x, checkedMode);
        return checkedMode
                ? makeCheckedIntOperation(leftValue, rightValue)
                : makeIntOperation(leftValue, rightValue);
    }

    @Override
    public BigDecimal evaluate(BigDecimal x, boolean checkedMode) {
        final BigDecimal leftValue = left.evaluate(x, checkedMode);
        final BigDecimal rightValue = right.evaluate(x, checkedMode);
        return checkedMode
                ? makeCheckedDecimalOperation(leftValue, rightValue)
                : makeDecimalOperation(leftValue, rightValue);
    }

    @Override
    public int evaluate(int x, int y, int z, boolean checkedMode) {
        final int leftValue = left.evaluate(x, y, z, checkedMode);
        final int rightValue = right.evaluate(x, y, z, checkedMode);
        return checkedMode
                ? makeCheckedIntOperation(leftValue, rightValue)
                : makeIntOperation(leftValue, rightValue);
    }

    protected abstract int makeIntOperation(int a, int b);

    protected int makeCheckedIntOperation(int a, int b) {
        return makeIntOperation(a, b);
    }

    protected abstract BigDecimal makeDecimalOperation(BigDecimal a, BigDecimal b);

    protected BigDecimal makeCheckedDecimalOperation(BigDecimal a, BigDecimal b) {
        return makeDecimalOperation(a, b);
    }

    protected String getErrorMessage(int a, int b) {
        return a + " " + getExpressionSign() + " " + b;
    }

    protected String getErrorMessage(BigDecimal a, BigDecimal b) {
        return a + " " + getExpressionSign() + " " + b;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(left);
        sb.append(' ').append(getExpressionSign()).append(' ');
        sb.append(right).append(')');
        return sb.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();

        if (left.getPriority() > this.getPriority()) {
            sb.append('(').append(left.toMiniString()).append(')');
        } else {
            sb.append(left.toMiniString());
        }

        sb.append(' ').append(getExpressionSign()).append(' ');

        if (right.getPriority() > this.getPriority() ||
                (right.getPriority() == this.getPriority() && this.orderIsImportant()) ||
                (right.canBeDecimalResult() && this.canChangeDecimalRounding())) {
            sb.append('(').append(right.toMiniString()).append(')');
        } else {
            sb.append(right.toMiniString());
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof BinaryOperation) {
            BinaryOperation that = (BinaryOperation) obj;
            return this.left.equals(that.left) && this.right.equals(that.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ((left.hashCode() * 17 + super.hashCode()) * 31 + right.hashCode()) * 29;
    }
}
