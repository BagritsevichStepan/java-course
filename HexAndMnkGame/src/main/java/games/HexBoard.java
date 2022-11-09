package games;

import games.Dsu.Dsu;

public class HexBoard extends GeneralBoard {
    private final Dsu dsu;

    public HexBoard(int n) {
        super(2 * n - 1, 2 * n - 1, new int[]{0, -1, -1, 0, 1, 1}, new int[]{-2, -1, 1, 2, 1, -1});

        n = 2 * n - 1;
        HexDsuNode[] sets = new HexDsuNode[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sets[getSetNumber(i, j)] = new HexDsuNode(new boolean[]{
                        (i <= n / 2 && j == leftmostColumnInRow(i)),
                        (i <= n / 2 && j == rightmostColumnInRow(i)),
                        (i >= n / 2 && j == leftmostColumnInRow(i)),
                        (i >= n / 2 && j == rightmostColumnInRow(i))
                });
            }
        }

        dsu = new Dsu(sets);
    }

    public int leftmostColumnInRow(int row) {
        return Math.abs(n / 2 - row);
    }

    public int rightmostColumnInRow(int row) {
        return m - Math.abs(n / 2 - row) - 1;
    }

    @Override
    protected boolean cellOnBoard(int row, int col) {
        boolean res = (0 <= row && row < n &&
                leftmostColumnInRow(row) <= col && col <= rightmostColumnInRow(row) &&
                ((getFirstN() % 2 == 0) == (row % 2 != col % 2)));
        return res;
    }

    private int getFirstN() {
        return (n + 1) / 2;
    }

    @Override
    protected void updateCell(Move curMove) {
        int row = curMove.getRow(), col = curMove.getCol();
        board[row][col] = curMove.getValue();

        dsu.updateSetValue(getSetNumber(row, col), curMove.getValue());
        for (int i = 0; i < rowDelta.length; i++) {
            int nextRow = row + rowDelta[i];
            int nextCol = col + columnDelta[i];
            if (cellOnBoard(nextRow, nextCol)) {
                dsu.unionSets(getSetNumber(row, col), getSetNumber(nextRow, nextCol));
            }
        }
    }

    private int getSetNumber(int row, int col) {
        return row * n + col;
    }

    @Override
    protected boolean hasWon(Move curMove) {
        Object setValue = dsu.getSetValue(getSetNumber(curMove.getRow(), curMove.getCol()));
        if (setValue instanceof boolean[]) {
            boolean[] onISide = (boolean[]) setValue;
            return (curMove.getValue() == Cell.X && onISide[0] && onISide[3]) ||
                    (curMove.getValue() == Cell.O && onISide[1] && onISide[2]);
        }

        throw new AssertionError("Invalid data type of set value");
    }
}