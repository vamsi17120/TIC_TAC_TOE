package Strategy;

import Models.Board;
import Models.Move;
import Models.Player;

public interface WinningStrategy {

    public boolean checkWinner(Move move, Board board);

}
