package com.assignment.hitman.writer;

/**
 * @author ashutoshp
 */
public interface Writer {
    void writeInfoMsg(String msg, Object... obj);
    void writeErrorMsg(String msg, Object... obj);
    void writeInputMsg(String msg, Object... obj);
}
