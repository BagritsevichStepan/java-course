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
    public int evaluate(int x) {
        return makeIntOperation(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeIntOperation(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return makeDecimalOperation(left.evaluate(x), right.evaluate(x));
    }

    protected abstract int makeIntOperation(int a, int b);

    protected abstract BigDecimal makeDecimalOperation(BigDecimal a, BigDecimal b);

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
