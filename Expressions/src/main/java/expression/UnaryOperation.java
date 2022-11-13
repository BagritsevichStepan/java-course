package expression;

import java.math.BigDecimal;

public abstract class UnaryOperation extends Operation {
    protected final ExpressionPriority expression;

    protected UnaryOperation(ExpressionPriority expression, Operator operator) {
        super(operator);
        this.expression = expression;
    }

    @Override
    public int evaluate(int x, boolean checkedMode) {
        int expressionValue = expression.evaluate(x, checkedMode);
        return checkedMode
                ? makeCheckedIntOperation(expressionValue)
                : makeIntOperation(expressionValue);
    }

    @Override
    public BigDecimal evaluate(BigDecimal x, boolean checkedMode) {
        BigDecimal expressionValue = expression.evaluate(x, checkedMode);
        return checkedMode
                ? makeCheckedDecimalOperation(expressionValue)
                : makeDecimalOperation(expressionValue);
    }

    @Override
    public int evaluate(int x, int y, int z, boolean checkedMode) {
        int expressionValue = expression.evaluate(x, y, z, checkedMode);
        return checkedMode
                ? makeCheckedIntOperation(expressionValue)
                : makeIntOperation(expressionValue);
    }

    protected abstract int makeIntOperation(int a);

    protected int makeCheckedIntOperation(int a) {
        return makeIntOperation(a);
    }

    protected abstract BigDecimal makeDecimalOperation(BigDecimal a);

    protected BigDecimal makeCheckedDecimalOperation(BigDecimal a) {
        return makeDecimalOperation(a);
    }

    protected String getErrorMessage(int a) {
        return getExpressionSign() + "(" + a + ")";
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getExpressionSign()).append('(').append(expression).append(')');
        return sb.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getExpressionSign());
        if (expression instanceof BinaryOperation) {
            sb.append('(').append(expression.toMiniString()).append(')');
        } else {
            sb.append(' ').append(expression.toMiniString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof UnaryOperation) {
            UnaryOperation that = (UnaryOperation) obj;
            return this.expression.equals(that.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (expression.hashCode() * 17 + super.hashCode()) * 31;
    }
}
