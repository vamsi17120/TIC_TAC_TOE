package Models;

public class bot extends Player {
  BotDifficultyLevel botDifficultyLevel = BotDifficultyLevel.EASY;

  bot(int id, String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
    super(id, name, PlayerType.BOT, symbol);
    this.botDifficultyLevel = botDifficultyLevel;
  }

  public BotDifficultyLevel getBotDifficultyLevel() {
    return botDifficultyLevel;
  }

  public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
    this.botDifficultyLevel = botDifficultyLevel;
  }

}
