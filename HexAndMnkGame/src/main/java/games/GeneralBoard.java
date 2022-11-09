package games;

public abstract class GeneralBoard implements Board, Position {
    protected final Cell[][] board;
    protected final int n;
    protected final int m;
    private int cntEmptyCells;
    private Cell turn;
    protected final int[] rowDelta;
    protected final int[] columnDelta;

    GeneralBoard(int n, int m, int[] rowDelta, int[] columnDelta) {
        this.n = n;
        this.m = m;
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;

        board = new Cell[n][m];
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                board[i][j] = cellOnBoard(i, j) ? Cell.EMPTY : Cell.SPACE;
            }
        }


        cntEmptyCells = n * m;
        turn = Cell.X;
    }

    @Override
    public int getRowCount() {
        return n;
    }

    @Override
    public int getColumnCount() {
        return m;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public boolean moveIsValid(Move curMove) {
        return cellOnBoard(curMove.getRow(), curMove.getCol()) &&
                board[curMove.getRow()][curMove.getCol()] == Cell.EMPTY;
    }

    protected abstract boolean cellOnBoard(int row, int col);

    @Override
    public Position getPosition() {
        return new AlternativePosition(this);
    }

    @Override
    public GameResult makeMove(Move curMove) {
        if (!moveIsValid(curMove)) {
            return GameResult.LOSS;
        }

        cntEmptyCells--;
        updateCell(curMove);

        if (isDraw()) {
            return GameResult.DRAW;
        }

        if (hasWon(curMove)) {
            return GameResult.WIN;
        }

        turn = (turn == Cell.X) ? Cell.O : Cell.X;
        return GameResult.UNKNOWN;
    }

    protected abstract void updateCell(Move curMove);

    private boolean isDraw() {
        return cntEmptyCells == 0;
    }

    protected abstract boolean hasWon(Move curMove);

    @Override
    public String toString() {
        int necessaryPrefOutputLength = String.valueOf(n).length() + 1;
        StringBuilder sb = new StringBuilder(addSpaces(" ", necessaryPrefOutputLength));
        for (int j = 0; j < m; j++) {
            sb.append(j + 1);
        }
        sb.append(System.lineSeparator());

        for (int i = 0; i < n; i++) {
            sb.append(addSpaces(String.valueOf(i + 1), necessaryPrefOutputLength));
            for (Cell curCell: board[i]) {
                sb.append(Cell.getString(curCell));
            }
            sb.append(System.lineSeparator());
        }

        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }

    private String addSpaces(String curString, int necessaryLength) {
        return curString + new String(new char[necessaryLength - curString.length()]).replace('\0', ' ');
    }
}
