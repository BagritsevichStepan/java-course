package games;

public interface Board {
    Position getPosition();
    GameResult makeMove(Move curMove);
}
