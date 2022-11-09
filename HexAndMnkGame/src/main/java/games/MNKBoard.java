package games;

import java.util.Arrays;

public class MNKBoard extends GeneralBoard {
    private final int k;

    public MNKBoard(int n, int m, int k) {
        super(n, m, new int[]{0, 0, -1, 1, -1, 1, -1, 1}, new int[]{-1, 1, 0, 0, -1, 1, 1, -1});
        this.k = k;
    }

    public MNKBoard() {
        this(3, 3, 3);
    }

    @Override
    protected boolean cellOnBoard(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < m;
    }

    @Override
    protected void updateCell(Move curMove) {
        board[curMove.getRow()][curMove.getCol()] = curMove.getValue();
    }

    @Override
    protected boolean hasWon(Move curMove) {
        int row = curMove.getRow(), col = curMove.getCol();
        for (int i = 0; i < rowDelta.length; i += 2) {
            int turnCellsCount = getTurnCellsCount(row, col, rowDelta[i], columnDelta[i]) +
                    getTurnCellsCount(row, col, rowDelta[i + 1], columnDelta[i + 1]) - 1;

            if (turnCellsCount >= k) {
                return true;
            }
        }
        return false;
    }

    private int getTurnCellsCount(int row, int col, int rowDelta, int colDelta) {
        int turnCellsCount = 0;
        while (cellOnBoard(row, col) && board[row][col] == getTurn()) {
            turnCellsCount++;
            row += rowDelta;
            col += colDelta;
        }
        return turnCellsCount;
    }
}
