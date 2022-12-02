import games.*;

public class Example {
    public static void main(String[] args) {
        // HEX game 4x4 example
        // Human player vs Random player
        final Game hexGame = new Game(
                new HexBoard(4),
                new HumanPlayer(),
                new RandomPlayer()
        );
        System.out.println("Hex game result: " + hexGame.play(true));

        // TicTacToe game example
        // This is MNK game with n=3, m=3 and k=3
        // Sequential player vs Human player
        final Game ticTacToeGame = new Game(
                new TicTacToeBoard(),
                new SequentialPlayer(),
                new HumanPlayer()
        );
        System.out.println("TicTacToe game result: " + ticTacToeGame.play(true));

        // MNK game example
        // n=4, m=5, k=4
        // Sequential player vs Random player
        final Game mnkGame = new Game(
                new MNKBoard(4, 5, 4),
                new SequentialPlayer(),
                new RandomPlayer()
        );
        System.out.println("Mnk game result: " + mnkGame.play(true));
    }
}
