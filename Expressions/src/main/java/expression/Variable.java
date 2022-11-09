package expression;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class Variable implements ExpressionPriority {
    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public int getPriority() {
        return Operator.getOperatorPriority(Operator.VARIABLE);
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (variable) {
            case "x": return x;
            case "y": return y;
            case "z": return z;
        }

        throw new NoSuchElementException("Variable " + variable + " not found");
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return x;
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
        return variable;
    }

    @Override
    public String toMiniString() {
        return variable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            Variable that = (Variable) obj;
            return this.variable.equals(that.variable);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return variable.hashCode();
    }
}
