package games;

public class SequentialPlayer implements Player {
    @Override
    public Move makeMove(Position board) {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                final Move curMove = new Move(i, j, board.getTurn());
                if (board.moveIsValid(curMove)) {
                    return curMove;
                }
            }
        }
        throw new AssertionError("No valid moves");
    }
}
