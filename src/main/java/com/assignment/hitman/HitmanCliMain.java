package com.assignment.hitman;

import com.assignment.hitman.controller.GameController;
import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.util.MessageConstants;
import com.assignment.hitman.writer.Writer;
import com.assignment.hitman.writer.WriterFactory;

import java.sql.SQLException;

/** @author ashutoshp */
public class HitmanCliMain {

    public static void main(String[] args) throws SQLException {
        Writer writer = WriterFactory.getWriter();
        DBConfiguration.initializeDB();
        writer.writeInfoMsg(MessageConstants.GAME_START);
        GameController gameController = new GameController();
        gameController.startGame();
    }
}
