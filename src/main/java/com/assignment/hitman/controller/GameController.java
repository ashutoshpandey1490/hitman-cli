package com.assignment.hitman.controller;

import com.assignment.hitman.reader.Reader;
import com.assignment.hitman.reader.ReaderFactory;
import com.assignment.hitman.util.GameUtils;
import com.assignment.hitman.util.MessageConstants;
import com.assignment.hitman.writer.Writer;
import com.assignment.hitman.writer.WriterFactory;

import java.sql.SQLException;

/** @author ashutoshp */
public class GameController {

  private static final Writer writer = WriterFactory.getWriter();
  private static final Reader reader = ReaderFactory.getReader();
  private static  PlayerController playerController = new PlayerController();

  public void startGame() throws SQLException {
    PlayerController playerController = new PlayerController();
    Integer playerCount = playerController.getCountOfPlayer();
    if (playerCount > 0) {
      resumeGame();
    } else {
      startFresh();
    }
  }

  public void resumeGame() throws SQLException {
    writer.writeInputMsg(MessageConstants.START);
    writer.writeInputMsg(MessageConstants.RESUME);
    writer.writeInputMsg(MessageConstants.EXIT_OPTION);
    int input = reader.readInt();
    switch (input) {
      case 1:
      {
        playerController.createPlayer();
      }
      case 2:
      {
        playerController.resumeWithPlayer();
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
    writer.writeInputMsg(MessageConstants.START);
    writer.writeInputMsg(MessageConstants.EXIT);
    int input = reader.readInt();
    switch (input) {
      case 1:
      {
        playerController.createPlayer();
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
