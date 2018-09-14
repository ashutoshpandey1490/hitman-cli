package com.assignment.hitman.controller;

import com.assignment.hitman.reader.Reader;
import com.assignment.hitman.reader.ReaderFactory;
import com.assignment.hitman.util.GameUtils;
import com.assignment.hitman.util.MessageConstants;
import com.assignment.hitman.writer.Writer;
import com.assignment.hitman.writer.WriterFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** @author ashutoshp */
public class GameController {

  private GameController() {}

  private static class GameControllerCreator {
     private static final GameController INSTANCE = new GameController();
  }

  public static GameController getGameController() {
    return GameControllerCreator.INSTANCE;
  }

  private static final Writer writer = WriterFactory.getWriter();
  private static final Reader reader = ReaderFactory.getReader();

  public void startGame() throws SQLException {
    Integer playerCount = PlayerController.getPlayerController().getCountOfPlayer();
    if (playerCount > 0) {
      resumeGame();
    } else {
      startFresh();
    }
  }

  public void resumeGame() throws SQLException {
    List<String> resumeGameOptions = new ArrayList<>();
    resumeGameOptions.add(MessageConstants.START);
    resumeGameOptions.add(MessageConstants.RESUME);
    resumeGameOptions.add(MessageConstants.EXIT);
    GameUtils.printOptions(resumeGameOptions);
    int input = reader.readInt();
    switch (input) {
      case 1:
      {
        PlayerController.getPlayerController().createPlayer();
      }
      case 2:
      {
        PlayerController.getPlayerController().resumeWithPlayer();
      }
      case 3:
      {
        GameUtils.stopGame();
      }
      default:
      {
        writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
        resumeGame();
      }
    }
  }

  private void startFresh() throws SQLException {
    List<String> freshGameOptions = new ArrayList<>();
    freshGameOptions.add(MessageConstants.START);
    freshGameOptions.add(MessageConstants.EXIT);
    GameUtils.printOptions(freshGameOptions);
    int input = reader.readInt();
    switch (input) {
      case 1:
      {
        PlayerController.getPlayerController().createPlayer();
      }
      case 2:
      {
        GameUtils.stopGame();
      }
      default:
      {
        writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
        startFresh();
      }
    }
  }
}
