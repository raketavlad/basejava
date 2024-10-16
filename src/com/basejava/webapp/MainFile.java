package com.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {

        String filePath = ".\\.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File(".\\src\\com\\basejava\\webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : dir.list()) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Домашнее задание
        System.out.println("\n\nРезультат домашнего задания");
        String pathName = "D:\\AllJava\\basejava";
        printAllFiles(pathName, "- ");
    }

    public static void printAllFiles(String pathName, String marker) {
        File dir = new File(pathName);
        if (dir.isDirectory()) {
            String[] list = dir.list();
            if (list != null) {
                for (String name : list) {
                    System.out.println(marker + name);
                    String newPathName = pathName + "\\" + name;
                    if (new File(newPathName).isDirectory()) {
                        printAllFiles(newPathName, "  " + marker);
                    }
                }
            }
        }
    }
}
