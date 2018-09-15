package com.assignment.hitman.util;

import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.reader.Reader;
import com.assignment.hitman.reader.ReaderFactory;
import com.assignment.hitman.writer.Writer;
import com.assignment.hitman.writer.WriterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/** @author ashutoshp */
public class GameUtils {

    private static Reader reader = ReaderFactory.getReader();
    private static Writer writer = WriterFactory.getWriter();

    public static void stopGame() {
        DBConfiguration.stopDB();
        reader.dispose();
        try {
            // Giving time of 1 second to clean up resources before shutting down JVM.
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void printOptions(List<String> options) {
        IntStream.range(0, options.size())
                .forEach(i -> writer.writeInputMsg(i + 1 + " - " + options.get(i)));
    }
}
