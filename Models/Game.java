package Models;

import java.util.ArrayList;
import java.util.List;

import Strategy.WinningStrategy;

public class Game {

    Board board;

    List<Player> players;

    List<Move> moves;

    Player winner;

    int nextPlayerIndex;

    GameState gameState;

    List<WinningStrategy> winningStrategies;

    public Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.gameState = GameState.IN_PROGRESS;
        this.winner = null;
        this.nextPlayerIndex = 0;
        this.moves = new ArrayList<>();
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        int dimension;

        List<Player> players;

        List<WinningStrategy> winningStrategies;

        Builder() {
            this.dimension = 3;
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game Build() {
            return new Game(this.dimension, this.players, this.winningStrategies);
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public String getWinner() {
        return winner.getName();
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void displayBoard() {
        board.display();
    }

    public void makeMove() {
        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println("it is " + currentPlayer.getName() + "s turn. make move");

        Move move = currentPlayer.makeMove(board);

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell celltochange = board.getGrid().get(row).get((col));

        celltochange.setCellState(CellState.FILLED);
        celltochange.setSymbol(currentPlayer.getSymbol());

        Move finalMoveObject = new Move(celltochange, currentPlayer);
        moves.add(finalMoveObject);
        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();

        if (checkWinner(board, move)) {
            gameState = GameState.SUCCESS;
            winner = currentPlayer;
        } else if (moves.size() == board.getSize() * board.getSize()) {
            gameState = GameState.DRAW;
        }

    }

    public boolean checkWinner(Board board, Move move) {
        for (WinningStrategy WinningStrategy : winningStrategies) {
            if (WinningStrategy.checkWinner(move, board)) {
                return true;
            }

        }
        return false;
    }
}
