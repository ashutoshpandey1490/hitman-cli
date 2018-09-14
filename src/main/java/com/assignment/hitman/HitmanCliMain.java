package com.assignment.hitman;

import com.assignment.hitman.controller.GameController;
import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.util.MessageConstants;
import com.assignment.hitman.writer.Writer;
import com.assignment.hitman.writer.WriterFactory;

import java.sql.SQLException;

/** @author ashutoshp */
public class HitmanCliMain {

    private static final Writer writer = WriterFactory.getWriter();

    public static void main(String[] args) throws SQLException {
        DBConfiguration.initializeDB();
        writer.writeInfoMsg(MessageConstants.GAME_START);
        GameController.getInstance().startGame();
    }
}
