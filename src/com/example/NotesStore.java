package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NotesStore {

    private final File file;

    public NotesStore(String path) {
        this.file = new File(path);
    }

    // Загружаем все заметки
    public List<String> load() {
        List<String> notes = new ArrayList<>();

        if (!file.exists()) {
            return notes;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                notes.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        return notes;
    }

    // Сохраняем список в файл
    private void save(List<String> notes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String n : notes) {
                bw.write(n);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }

    // Добавить новую заметку
    public void add(String text) {
        List<String> notes = load();
        int id = notes.size() + 1; // следующий ID
        notes.add(id + ";" + text);
        save(notes);
    }

    // Получить список
    public List<String> list() {
        return load();
    }
}