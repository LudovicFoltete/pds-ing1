package org.ing1.pds;

import java.lang.reflect.Field;
import java.util.Scanner;

public class View {

    private static View ourInstance = new View();
    private Scanner sc = new Scanner(System.in);

    public static View getInstance() { return ourInstance; }

    private View() {}

    public void display(String text) {
        System.out.println(text);
    }

    public void readConsole(Request request) {
        String[] words = sc.nextLine().split(" ");
        if (words[0].equals("add")) {
            try {
                Class c = Class.forName("org.ing1.pds."+words[1]);
                Field[] fields = c.getDeclaredFields();
                String[] values = new String[fields.length - 1];
                for (int i = 0; i < fields.length-1; i++) {
                    System.out.print(fields[i+1].getName() + " : ");
                    values[i] = sc.nextLine();
                }
                Controller.getInstance().add(words[1], values, request);
            } catch (ClassNotFoundException e) {
                System.err.println("There are no" + words[1]);
            }
        }
        else if (words[0].equals("show")) {
            Controller.getInstance().show(words[1], request);
        }
        else {
            System.err.println("Unknown command");
        }
    }
}
