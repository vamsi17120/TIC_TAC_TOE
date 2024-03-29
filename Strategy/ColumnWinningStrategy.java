package Strategy;

import java.util.HashMap;

import Models.Board;
import Models.Move;
import Models.Player;
import Models.Symbol;

public class ColumnWinningStrategy implements WinningStrategy {

    HashMap<Integer, HashMap<Symbol, Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        // 0 -> { 'X' -> 1} , {'O' -> 2}
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if (!counts.containsKey(col)) {
            counts.put(col, new HashMap<>());
        }

        HashMap<Symbol, Integer> currentCol = counts.get(col);

        if (!currentCol.containsKey(symbol)) {
            currentCol.put(symbol, 0);
        }
        currentCol.put(symbol, currentCol.get(symbol) + 1);
        if (currentCol.get(symbol) == board.getSize())
            return true;

        return false;

    }
}
