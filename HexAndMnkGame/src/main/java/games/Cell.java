package games;

import java.util.Map;

public enum Cell {
    X, O, EMPTY, SPACE;

    private final static Map<Cell, String> cellToString = Map.of(
            X, "X",
            O, "0",
            EMPTY, ".",
            SPACE, " "
    );

    public static String getString(Cell curCell) {
        String res = cellToString.get(curCell);
        if (res != null) {
            return res;
        }

        throw new AssertionError("Unknown cell type " + curCell);
    }
}
