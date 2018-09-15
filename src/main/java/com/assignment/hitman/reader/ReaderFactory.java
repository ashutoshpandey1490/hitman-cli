package com.assignment.hitman.reader;

/**
 * Factory pattern implementation to hide the details of object instantiation.
 *
 * @author ashutoshp
 */
public class ReaderFactory {

    public static Reader getReader() {
        return ReaderCreator.reader;
    }

    private static class ReaderCreator {
        static Reader reader = new ConsoleReader();
    }
}
