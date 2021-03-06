package com.assignment.hitman.writer;

import static com.assignment.hitman.util.ColorConstants.*;

/**
 * Implementation method to write to the Console. VM argument is being passed to decide on printing
 * of colors. Windows command prompt does not support acsii colors but running the main
 * class in IntelliJ will give you the colorful output.
 *
 * @author ashutoshp
 */
public class ConsoleWriter implements Writer {

    private static boolean colored = true;

    static {
        String value = System.getProperty("colored");
        if (value != null) {
            colored = Boolean.valueOf(value);
        }
    }

    @Override
    public void writeInfoMsg(String msg, Object... obj) {
        if (colored) {
            System.out.println(ANSI_GREEN + String.format(msg, obj) + ANSI_RESET);
        } else {
            System.out.println(String.format(msg, obj));
        }
    }

    @Override
    public void writeErrorMsg(String msg, Object... obj) {
        if (colored) {
            System.out.println(ANSI_RED + String.format(msg, obj) + ANSI_RESET);
        } else {
            System.out.println(String.format(msg, obj));
        }
    }

    @Override
    public void writeInputMsg(String msg, Object... obj) {
        if (colored) {
            System.out.println(ANSI_PURPLE + String.format(msg, obj) + ANSI_RESET);
        } else {
            System.out.println(String.format(msg, obj));
        }
    }
}
