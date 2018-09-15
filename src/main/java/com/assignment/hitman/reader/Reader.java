package com.assignment.hitman.reader;

/**
 * An interface to define method to take input from the player.
 *
 * @author ashutoshp
 */
public interface Reader extends Disposable {
    int readInt();

    String readString();
}
