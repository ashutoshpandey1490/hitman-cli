package com.assignment.hitman.reader;

/** @author ashutoshp */
public class ReaderFactory {

    public static Reader getReader() {
        return ReaderCreator.reader;
    }

    private static class ReaderCreator {
        static Reader reader = new ConsoleReader();
    }
}
