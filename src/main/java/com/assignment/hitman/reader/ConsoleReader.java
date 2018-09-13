package com.assignment.hitman.reader;

import java.util.Scanner;

/** @author ashutoshp */
public class ConsoleReader implements Reader {

    private static Scanner scanner = new Scanner(System.in);

    @Override
    public int readInt() {
        return scanner.nextInt();
    }

    @Override
    public String readString() {
        return scanner.next();
    }

    @Override
    public void dispose() {
        scanner.close();
    }
}
