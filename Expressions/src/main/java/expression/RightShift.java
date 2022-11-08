package expression;

public class RightShift extends ShiftOperation {
    public RightShift(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.RIGHT_SHIFT);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return a >> b;
    }
}
