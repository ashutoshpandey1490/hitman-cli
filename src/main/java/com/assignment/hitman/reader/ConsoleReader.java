package com.assignment.hitman.reader;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Actual implementation to take input from player from console. This implementation is used in
 * entire application to make sure input mechanism is in one place.
 *
 * @author ashutoshp
 */
public class ConsoleReader implements Reader {

    private static Scanner scanner = new Scanner(System.in);

    @Override
    public int readInt() {
        int value;
        try {
            value = scanner.nextInt();
        } catch (InputMismatchException exp) {
            scanner.nextLine();
            value = -1;
        }
        return value;
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
