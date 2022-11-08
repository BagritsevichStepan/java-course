package expression;

import java.math.BigDecimal;

public class Const implements ExpressionPriority {
    private final Number value;

    public Const(Number value) {
        this.value = value;
    }

    public Const(int value) {
        this.value = value;
    }

    public Const(BigDecimal value) {
        this.value = value;
    }

    @Override
    public int getPriority() {
        return Operator.getOperatorPriority(Operator.CONST);
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return new BigDecimal(value.toString());
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

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public String toMiniString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const) {
            Const that = (Const) obj;
            return this.value.equals(that.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
