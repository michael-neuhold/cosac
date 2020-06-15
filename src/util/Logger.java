package util;

import java.util.Collection;

public class Logger {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void serverDB(String msg) {
        System.out.println(ANSI_GREEN + msg + ANSI_RESET);
    }

    public static void dataTransfer(Collection collection) {
        for(Object item : collection) System.out.println("\t| " + ANSI_BLUE + item + ANSI_RESET);
    }

    public static void dataTransfer(Object object) {
        System.out.println("\t| " + ANSI_BLUE + object + ANSI_RESET);
    }

    public static void clientRMI(String msg) {
        System.out.println(ANSI_YELLOW + msg + ANSI_RESET);
    }

    public static void clientStatus(String msg) {
        System.out.println(ANSI_GREEN + msg + ANSI_RESET);
    }



    public static void error(String msg) {
        System.out.println(ANSI_RED + msg + ANSI_RESET);
    }

}
