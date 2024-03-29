package Controllers;

import java.util.List;

import Models.Game;
import Models.GameState;
import Models.Player;
import Strategy.WinningStrategy;

public class GameController {

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        return Game.getBuilder().setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .Build();
    }

    public void displayboard(Game game) {
        game.displayBoard();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public GameState checkState(Game game) {
        return game.getGameState();
    }

    public String getWinner(Game game) {
        return game.getWinner();
    }

    public void undo() {

    }

}
