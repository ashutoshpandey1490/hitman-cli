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

  Writer writer = WriterFactory.getWriter();
  Reader reader = ReaderFactory.getReader();

  public void startGame() throws SQLException {
    writer.writeInputMsg(MessageConstants.START);
    writer.writeInputMsg(MessageConstants.EXIT);
    int input = reader.readInt();
    switch (input) {
      case 1:
        {
          PlayerController playerController = new PlayerController();
          playerController.createPlayer();
        }
      case 2:
        {
          GameUtils.stopGame();
        }
      default:
        {
          writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
          startGame();
        }
    }
  }
}
