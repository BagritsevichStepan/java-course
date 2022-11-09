package games;

public class Game {
    final private Board board;
    final private Player player1;
    final private Player player2;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public String play(boolean enableLogging) {
        while (true) {
            int result = moveResult(player1, 1, enableLogging);
            if (result >= 0) {
                return gameResultToString(result);
            }

            result = moveResult(player2, 2, enableLogging);
            if (result >= 0) {
                return gameResultToString(result);
            }
        }
    }

    private String gameResultToString(int result) {
        if (result == 0) {
            return "draw";
        }
        if (result == 1) {
            return "First player won";
        }
        if (result == 2) {
            return "Second player won";
        }

        throw new AssertionError("Unknown game result: " + result);
    }

    private int moveResult(Player player, int playerNumber, boolean enableLogging) {
        final Move move = player.makeMove(board.getPosition());
        final GameResult result = board.makeMove(move);

        if (enableLogging) {
            System.out.println();
            System.out.println("Player: " + playerNumber);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }

        switch (result) {
            case WIN:
                return playerNumber;
            case LOSS:
                return 3 - playerNumber;
            case DRAW:
                return 0;
            case UNKNOWN:
                return -1;
            default:
                throw new AssertionError("Unknown makeMove result " + result);
        }
    }
}
