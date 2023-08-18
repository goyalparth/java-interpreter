import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.Math;

public class Menu {
    public static String fname = "";
    public static Scanner in = null;

    public static void main(String[] args) {
        fname = "Task1b.txt";
        runIt();

    }

    public static void runIt() {
        System.out.print("\f");
        String menuOpt = "";
        in = new Scanner(System.in);
        do {
            System.out.print("\nST2-2023 Assignment 1\n");
            System.out.print("(" + fname + ")\n");
            System.out.print("Q -  Exit/Quit\n");
            System.out.print("W -  Who Am I (Task1)\n");
            System.out.print("E -  Execute SAIL2023File\n");
            System.out.print("S  - Set File name\n");
            // //
            System.out.print("Select Option:\n");
            menuOpt = in.nextLine().trim().toUpperCase();
            if (menuOpt.compareToIgnoreCase("W") == 0) {
                optW();
            }
            if (menuOpt.compareToIgnoreCase("E") == 0) {
                optE(true, false);
            }
            if (menuOpt.compareToIgnoreCase("S") == 0) {
                optS();
            }

        } while (menuOpt.compareToIgnoreCase("Q") != 0); // Note the != this time
        System.out.print("\nEnding Now\n");
    }

    public static void optW() {
        System.out.println("\nAuthor: Parth Goyal u3223149\n");
    }

    public static void optE(boolean showOpt, boolean dbg) {
        Interpreter interpreter = new Interpreter();
        interpreter.compile(fname);
    }

    public static void optS() {
        System.out.printf("Select Option:\n");
        System.out.printf("0- enter file name:\n");
        System.out.printf("1- Task1a:\n");
        System.out.printf("2- Task1b:\n");
        System.out.printf("3- Task1c:\n");
        System.out.printf("4- Task1d:\n"); // add more options if you find them usefull
        System.out.printf("5- Task2a:\n"); // add more options if you find them usefull
        System.out.printf("6- Task2b:\n"); // add more options if you find them usefull
        System.out.printf("7- Task3a:\n");
        System.out.printf("8- Task3b:\n");

        System.out.printf("9- Task4a:\n");
        System.out.printf("10- Task4b:\n");
        System.out.printf("11- Task4c:\n");

        System.out.printf("12- Task5a:\n");

        String opt = in.nextLine().trim().toUpperCase();

        switch (opt) {
            case "0":
                opt0();
                break;

            case "1":
                fname = "Task1a.txt";
                break;

            case "2":
                fname = "Task1b.txt";
                break;

            case "3":
                fname = "Task1c.txt";
                break;

            case "4":
                fname = "Task1d.txt";
                break;

            case "5":
                fname = "Task2a.txt";
                break;

            case "6":
                fname = "Task2b.txt";
                break;

            case "7":
                fname = "Task3a.txt";
                break;

            case "8":
                fname = "Task3b.txt";
                break;

            case "9":
                fname = "Task4a.txt";
                break;

            case "10":
                fname = "Task4b.txt";
                break;

            case "11":
                fname = "Task4c.txt";
                break;

            case "12":
                fname = "Task5a.txt";
                break;

        }
    }

    public static void opt0() {
        fname = "";
        while (fname.compareTo("") == 0) {
            System.out.printf("0- enter file name:\n");
            fname = in.nextLine().trim();
        }
        System.out.print("file set to " + fname);
    }

}