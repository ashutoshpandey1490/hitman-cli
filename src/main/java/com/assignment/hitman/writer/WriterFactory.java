package com.assignment.hitman.writer;

/**
 * Factory pattern implementation to hide the details of object instantiation.
 *
 * @author ashutoshp
 */
public class WriterFactory {

    public static Writer getWriter() {
        return WriterCreator.writer;
    }

    private static class WriterCreator {
        private static Writer writer = new ConsoleWriter();
    }
}
