package expression;

public abstract class Operation implements ExpressionPriority {
    protected final Operator operator;

    protected Operation(Operator operator) {
        this.operator = operator;
    }

    protected String getExpressionSign() {
        return Operator.getOperatorSign(operator);
    }

    @Override
    public int getPriority() {
        return Operator.getOperatorPriority(operator);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Operation) {
            Operation that = (Operation) obj;
            return this.operator == that.operator;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return operator.hashCode();
    }
}
