package expression;

public class LeftShift extends ShiftOperation {
    public LeftShift(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.LEFT_SHIFT);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return a << b;
    }
}
