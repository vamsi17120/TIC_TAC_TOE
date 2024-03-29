package Strategy;

import java.util.HashMap;

import Models.Board;
import Models.Move;
import Models.Player;
import Models.Symbol;

public class RowWinningStrategy implements WinningStrategy {

    HashMap<Integer, HashMap<Symbol, Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        // 0 -> { 'X' -> 1} , {'O' -> 2}
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        if (!counts.containsKey(row)) {
            counts.put(row, new HashMap<>());
        }

        HashMap<Symbol, Integer> currentRow = counts.get(row);

        if (!currentRow.containsKey(symbol)) {
            currentRow.put(symbol, 0);
        }
        currentRow.put(symbol, currentRow.get(symbol) + 1);
        if (currentRow.get(symbol) == board.getSize())
            return true;

        return false;

    }
}
