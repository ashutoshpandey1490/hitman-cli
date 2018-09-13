package com.assignment.hitman.util;

import com.assignment.hitman.reader.Reader;
import com.assignment.hitman.reader.ReaderFactory;
import com.assignment.hitman.writer.Writer;
import com.assignment.hitman.writer.WriterFactory;

import java.util.concurrent.TimeUnit;

/** @author ashutoshp */
public class GameUtils {

    private static Reader reader = ReaderFactory.getReader();
    private static Writer writer = WriterFactory.getWriter();

    public static void stopGame() {
        reader.dispose();
        try {
            // Giving time of 1 second to clean up resources before shutting down JVM.
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
