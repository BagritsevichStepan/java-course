package games;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Move makeMove(Position board) {
        Move curMove;
        do {
            curMove = new Move(
                    random.nextInt(board.getRowCount()),
                    random.nextInt(board.getColumnCount()),
                    board.getTurn()
            );
        } while (!board.moveIsValid(curMove));

        return curMove;
    }
}
