# HEX and MNK game
The project implements two games, [HEX](https://en.wikipedia.org/wiki/Hex_(board_game)) and [MNK game](https://en.wikipedia.org/wiki/M,n,k-game), which is a complicated version of the Tic-Tac-Toe game. In addition to the ability to select the size of the board, you can also choose what type of player will play the game: human player, random player or sequential player.

## Problem statement
1. [First part. MNK Game](#first-part)
2. [Second part. HEX Game](#second-part)

### First part
1. Implement the [MNK game](https://en.wikipedia.org/wiki/M,n,k-game)
2. The game must be run by the class `Game` using the method `play`
3. The class `Game` needs to be given the players and the board, on which the game will take place
4. The class `MNKBoard` is an implementation of the board for MNK game
5. The class `TicTacToeBoard` is an implementation of the board for Tic-Tac-Toe game or MNK game with $n = 3$, $m = 3$ and $k = 3$
6. Implement the players:
   + The class `HumanPlayer` implements the user with console input
   + The class `RandomPlayer` implements a player who makes random moves
   + The class `SequentialPlayer` implements a player who makes moves sequentially starting from the top left cell
7. Add error handling for user input (`HumanPlayer`). In case of an incorrect move, the user should be able to make another move
8. Add error handling for players (all players except `HumanPlayer`). In case of an incorrect move, the player automatically loses.
9. The board should process the move (check correctness, change state and determine the result) for $\mathcal{O}(k)$
10. Add logging to the method `play`. At logging after each move the board and the current result of the game should be displayed in the console

### Second part
1. Add the implementation of the [HEX game](https://en.wikipedia.org/wiki/Hex_(board_game))
2. The code that implements both games must be common
3. The class `HexBoard` is an implementation of the board for HEX game
4. The hex board should process the move (check correctness, change state and determine the result) for $\mathcal{O}(\log_{2}(n * m))$

## Examples
Run `Example.java`

As an example, three games will be run:
1. HEX game with board $4 \times 4$, human and random player. Logging is enabled
2. Tic-Tac-Toe game with sequential and human player. Logging is enabled
1. MNK game with sequential player, random player and the board with $n = 4$, $m = 5$, $k = 4$. Logging is enabled
