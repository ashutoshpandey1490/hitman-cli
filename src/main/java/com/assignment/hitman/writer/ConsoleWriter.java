package com.assignment.hitman.writer;

import static com.assignment.hitman.util.ColorConstants.*;

/**
 * @author ashutoshp
 */
public class ConsoleWriter implements  Writer {

    // TODO: ascii color codes do not work on windows command prompt but its working everywhere else. Possibly can take JVM arg
    // as input to print with colors.
    @Override
    public void writeInfoMsg(String msg, Object... obj) {
        System.out.println(ANSI_GREEN+String.format(msg,obj)+ANSI_RESET);
    }

    @Override
    public void writeErrorMsg(String msg, Object... obj) {
        System.out.println(ANSI_RED+String.format(msg,obj)+ANSI_RESET);
    }

    @Override
    public void writeInputMsg(String msg, Object... obj) {
        System.out.println(ANSI_PURPLE+String.format(msg,obj)+ANSI_RESET);
    }
}
