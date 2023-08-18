import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class Interpreter {
    public Hashtable<String, Integer> h = new Hashtable<>();
    public Boolean error;
    public int whileAt;
    public Boolean skipLine;
    private ArrayList<String> k = new ArrayList<>();

    public int currentLine = 0;

    public void compile(String name) {
        try {
            skipLine = true;
            File myObj = new File(name);
            Scanner myReader = new Scanner(myObj);

            int count1 = 0;
            int count2 = 0;

            error = false;
            whileAt = 0;

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                line = line.replace("\t", "");

                k.add(line);
                count1++;
                count2++;

                if (line.startsWith("/") || line.isEmpty()) {
                    count2--;
                }

            }
            System.out.println("\nReading code file " + name);
            System.out.println("Number of Lines Read in is            : " + count1);
            System.out.println("Number of Non Comment Lines Read in is: " + count2);
            System.out.println("----------Running code----------");

            while (currentLine < k.size()) {
                if (error) {
                    break;
                }
                generate(k.get(currentLine));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    private void interpret(String[] data) {
        try {
            if (skipLine == false) {
                if (data[0].equals("endif")) {
                    skipLine = true;
                }
            } else if ((data[0].equals("set") && (data[2].equals("to")))) {
                try {
                    int x = Integer.parseInt(data[3]);
                    h.put(data[1], x);
                } catch (Exception e) {
                    int x = h.get(data[3]);
                    h.put(data[1], x);
                }

            } else if ((data[0].equals("add")) && (data[2].equals("to"))) {
                // if ((data[2].equals("to"))) {
                try {
                    int i = Integer.parseInt(data[1]);
                    int x = h.get(data[3]);
                    h.put(data[3], i + x);
                } catch (Exception e) {
                    int i = h.get(data[1]);
                    int x = h.get(data[3]);
                    h.put(data[3], i + x);
                }
                // }
            } else if ((data[0].equals("subtract")) && (data[2].equals("from"))) {
                // if (data[2].equals("from")) {
                try {
                    int i = Integer.parseInt(data[1]);
                    int x = h.get(data[3]);
                    h.put(data[3], x - i);
                } catch (Exception e) {
                    int i = h.get(data[1]);
                    int x = h.get(data[3]);
                    h.put(data[3], x - i);
                }
                // }
            } else if (data[0].equals("print")) {
                if (data[1].contains("'")) {
                    for (int i = 1; i < data.length; i++) {
                        if (data[i].equals("'")) {
                            System.out.print(data[i].replaceAll("'", ""));
                        } else if (data[i].startsWith("'")) {
                            System.out.print(data[i].replaceAll("'", "") + " ");
                        } else if (data[i].endsWith("'")) {
                            System.out.print(data[i].replaceAll("'", ""));
                        } else {
                            System.out.print(data[i].replaceAll("'", "") + " ");
                        }
                    }
                } else if (data[1].equals("cls")) {
                    System.out.print('\u000C');
                    System.out.flush();
                } else {
                    try {
                        int i = Integer.parseInt(data[1]);
                        System.out.print(i);
                    } catch (Exception e) {
                        System.out.print(h.get(data[1]));
                    }
                }
            } else if (data[0].equals("println")) {
                if (data[1].contains("'")) {
                    for (int i = 1; i < data.length; i++) {
                        if (data[i].equals("'")) {
                            System.out.print(data[i].replaceAll("'", "") + " ");
                        } else if (data[i].startsWith("'")) {
                            System.out.print(data[i].replaceAll("'", "") + " ");
                        } else if (data[i].endsWith("'")) {
                            System.out.print(data[i].replaceAll("'", ""));
                        } else {
                            System.out.print(data[i].replaceAll("'", "") + " ");
                        }

                    }
                    System.out.println();
                } else if (data[1].equals("cls")) {
                    System.out.print('\u000C');
                    System.out.flush();
                } else {
                    try {
                        int i = Integer.parseInt(data[1]);
                        System.out.println(i);
                    } catch (Exception e) {
                        System.out.println(h.get(data[1]));
                    }
                }
            } else if (data[0].equals("//")) {
                // continue;
            } else if (data[0].equals("end")) {

            } else if (data[0].equals("")) {

            } else if (data[0].equals("endif")) {

            } else if (data[0].equals("multiply") && ((data[2].equals("by")))) {
                // if ((data[2].equals("by"))) {
                try {
                    int i = Integer.parseInt(data[1]);
                    int x = h.get(data[3]);
                    h.put(data[1], i * x);
                } catch (Exception e) {
                    int i = h.get(data[1]);
                    int x = h.get(data[3]);
                    h.put(data[1], i * x);
                }
                // }
            } else if (data[0].equals("divide") && ((data[2].equals("by")))) {
                // if ((data[2].equals("by"))) {
                try {
                    int i = Integer.parseInt(data[1]);
                    int x = h.get(data[3]);
                    h.put(data[1], i / x);
                } catch (Exception e) {
                    int i = h.get(data[1]);
                    int x = h.get(data[3]);
                    h.put(data[1], i / x);
                }
                // }
            } else if ((data[0].equals("if")) && ((data[4].equals("then")))) {
                // if ((data[4].equals("then"))) {
                if (data[2].equals("<")) {
                    int i = h.get(data[1]);
                    if (i < Integer.parseInt(data[3])) {

                    } else {
                        skipLine = false;
                    }
                } else if (data[2].equals(">")) {
                    int i = h.get(data[1]);
                    if (i > Integer.parseInt(data[3])) {

                    } else {
                        skipLine = false;
                    }
                } else if (data[2].equals("==")) {
                    int i = h.get(data[1]);
                    if (i == Integer.parseInt(data[3])) {

                    } else {
                        skipLine = false;
                    }
                } else {
                    System.out.println("Error at line: " + (currentLine + 1));
                    error = true;
                }
                // }
            } else {
                System.out.println("Error at line: " + (currentLine + 1));
                error = true;
            }
        } catch (Exception e) {
            System.out.println("Error at line: " + (currentLine + 1));
            error = true;
        }
    }

    private void generate(String line) {

        String[] data = line.split(" ");
        // System.out.println(data[2]);

        if ((data[0].equals("while"))) {
            // if (data[4].equals("do")) {
            whileAt = currentLine;
            // }

        } 
         else if ((data[0].equals("endwhile"))) {
            String[] whileData = k.get(whileAt).split(" ");
            int i = h.get(whileData[1]);
            if (i > Integer.parseInt(whileData[3])) {
                currentLine = whileAt;
            } else {
                whileAt = 0;
            }
        } else {
            interpret(data);
        }
        currentLine++;

    }
}
