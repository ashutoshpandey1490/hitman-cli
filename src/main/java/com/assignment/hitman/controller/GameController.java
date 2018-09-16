package com.assignment.hitman.controller;

import com.assignment.hitman.reader.Reader;
import com.assignment.hitman.reader.ReaderFactory;
import com.assignment.hitman.state.ResumeGame;
import com.assignment.hitman.state.StartFresh;
import com.assignment.hitman.util.GameUtils;
import com.assignment.hitman.util.MessageConstants;
import com.assignment.hitman.writer.Writer;
import com.assignment.hitman.writer.WriterFactory;

import java.sql.SQLException;

/**
 * Starting point of the game. Singleton implementation.
 *
 * @author ashutoshp
 */
public class GameController {

  private GameController() {}

  private static class GameControllerCreator {
    private static final GameController INSTANCE = new GameController();
  }

  public static GameController getInstance() {
    return GameControllerCreator.INSTANCE;
  }

  private static final Writer writer = WriterFactory.getWriter();
  private static final Reader reader = ReaderFactory.getReader();

  /**
   * If there is any player record in DB then "Resume game" option is displayed.
   *
   * @throws SQLException
   */
  public void startGame() throws SQLException {
    Integer playerCount = PlayerController.getInstance().getCountOfPlayer();
    if (playerCount > 0) {
      resumeGame();
    } else {
      startFresh();
    }
  }

  public void resumeGame() throws SQLException {
    GameUtils.printOptions(ResumeGame.getValues());
    int input = reader.readInt();
    if (ResumeGame.getOptionByKey(input) == null) {
      writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
      resumeGame();
    }
    switch (ResumeGame.getOptionByKey(input)) {
      case START:
      {
        PlayerController.getInstance().createPlayer();
        break;
      }
      case RESUME:
      {
        PlayerController.getInstance().resumeWithPlayer();
        break;
      }
      case EXIT:
      {
        GameUtils.stopGame();
        break;
      }
    }
  }

  private void startFresh() throws SQLException {
    GameUtils.printOptions(StartFresh.getValues());
    int input = reader.readInt();
    if (StartFresh.getOptionByKey(input) == null) {
      writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
      startFresh();
    }
    switch (StartFresh.getOptionByKey(input)) {
      case START:
      {
        PlayerController.getInstance().createPlayer();
        break;
      }
      case EXIT:
      {
        GameUtils.stopGame();
        break;
      }
    }
  }
}
