package games;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner input;

    public HumanPlayer() {
        input = new Scanner(System.in);
    }

    HumanPlayer(Scanner input) {
        this.input = input;
    }

    @Override
    public Move makeMove(Position board) {
        while (true) {
            System.out.println();
            System.out.println("Current board: ");
            System.out.println(board);

            System.out.println("Enter your move for " + Cell.getString(board.getTurn()));
            final Move curMove = new Move(input.nextInt() - 1, input.nextInt() - 1, board.getTurn());
            if (board.moveIsValid(curMove)) {
                return curMove;
            }

            System.out.println("Invalid move: " + curMove);
        }
    }
}
