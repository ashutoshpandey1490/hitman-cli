package com.assignment.hitman.writer;

/**
 * Interface to define methods to write to the console.
 *
 * @author ashutoshp
 */
public interface Writer {
    void writeInfoMsg(String msg, Object... obj);

    void writeErrorMsg(String msg, Object... obj);

    void writeInputMsg(String msg, Object... obj);
}
