package com.assignment.hitman.writer;

/** @author ashutoshp */
public class WriterFactory {

    public static Writer getWriter() {
        return WriterCreator.writer;
    }

    private static class WriterCreator {
        private static Writer writer = new ConsoleWriter();
    }
}
