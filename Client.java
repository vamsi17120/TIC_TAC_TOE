import java.util.ArrayList;
import java.util.List;

import Controllers.GameController;
import Models.Game;
import Models.GameState;
import Models.Player;
import Models.PlayerType;
import Models.Symbol;
import Strategy.ColumnWinningStrategy;
import Strategy.RowWinningStrategy;
import Strategy.WinningStrategy;

class client {
   public static void main(String[] args) {
      GameController gameController = new GameController();

      System.out.println("GAME IS STARTING");
      int dimension = 3;
      List<Player> players = new ArrayList<>();
      players.add(new Player(1, "vamshi", PlayerType.HUMAN, new Symbol('X')));
      players.add(new Player(2, "rAHUL", PlayerType.HUMAN, new Symbol('O')));
      List<WinningStrategy> winningStrategies = new ArrayList<>();
      winningStrategies.add(new RowWinningStrategy());
      winningStrategies.add(new ColumnWinningStrategy());

      Game game = gameController.startGame(dimension, players, winningStrategies);

      while (gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
         gameController.displayboard(game);
         gameController.makeMove(game);
         System.out.println();
      }

      if (gameController.checkState(game).equals(GameState.DRAW)) {
         System.out.println("the game ended on a draw");
      } else {
         System.out.println(gameController.getWinner(game) + "is the winner");
      }

   }
}