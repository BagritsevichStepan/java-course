package games;


public class AlternativePosition implements Position {
    private final Position board;

    AlternativePosition(Position board) {
        this.board = board;
    }

    @Override
    public int getRowCount() {
        return board.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return board.getColumnCount();
    }

    @Override
    public Cell getTurn() {
        return board.getTurn();
    }

    @Override
    public boolean moveIsValid(Move curMove) {
        return board.moveIsValid(curMove);
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
