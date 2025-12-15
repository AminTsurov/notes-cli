package com.example;

import java.util.List;

public class App {

    public static void main(String[] args) {

        String cmd = null;
        String text = null;

        // Разбор аргументов
        for (String arg : args) {
            if (arg.startsWith("--cmd=")) {
                cmd = arg.substring(6);
            } else if (arg.startsWith("--text=")) {
                text = arg.substring(7);
            }
        }

        NotesStore store = new NotesStore("data/notes.csv");

        if (cmd == null) {
            System.out.println("No command");
            return;
        }

        switch (cmd) {
            case "add":
                if (text == null) {
                    System.out.println("No text provided");
                    return;
                }
                store.add(text);
                System.out.println("Added");
                break;

            case "list":
                List<String> notes = store.list();
                if (notes.isEmpty()) {
                    System.out.println("(empty)");
                } else {
                    for (String n : notes) {
                        System.out.println(n);
                    }
                }
                break;
                
            case "count":
                System.out.println(store.list().size());
                break;

            default:
                System.out.println("Unknown command");
        }
    }
}