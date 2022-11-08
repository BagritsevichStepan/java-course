package expression;

public class UnsignedRightShift extends ShiftOperation {
    public UnsignedRightShift(ExpressionPriority left, ExpressionPriority right) {
        super(left, right, Operator.UNSIGNED_RIGHT_SHIFT);
    }

    @Override
    protected int makeIntOperation(int a, int b) {
        return a >>> b;
    }
}
