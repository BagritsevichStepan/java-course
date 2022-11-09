package games;

public interface Position {
    int getRowCount();
    int getColumnCount();
    Cell getTurn();
    boolean moveIsValid(Move curMove);
}
