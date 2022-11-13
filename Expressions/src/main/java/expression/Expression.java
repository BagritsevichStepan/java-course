package expression;

public interface Expression extends ToMiniString {
    int evaluate(int x, boolean checkedMode);
}