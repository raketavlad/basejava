package com.basejava.webapp;

import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.ListStorage;
import com.basejava.webapp.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Interactive test for com.basejava.webapp.storage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private final static Storage ARRAY_STORAGE = new ListStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume resume;
        while (true) {
            System.out.print("Введите одну из команд - (list | size | save fullName | delete uuid | get uuid | update uuid fullName | clear | exit): ");
            // Возврат строки, убираются пробелы пробелы сзади и спереди, к нижнему регистру, разбивка на эелементы
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            // Проверка, что введена правильная длинна команд (от одного слова до двух)
            if (params.length < 1 || params.length > 3) {
                System.out.println("Неверная команда.");
                continue;
            }
            String param = null;
            // Метод intern смотрит, есть ли введенный uuid в пуле строк и возвращает его, если его нет - создает
            if (params.length > 1) {
                param = params[1].intern();
            }
            //  В свиче проверяю первое введённое слово и выполняю соответствующее действие
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "save":
                    resume = new Resume(param);
                    ARRAY_STORAGE.save(resume);
                    printAll();
                    break;
                case "update":
                    resume = new Resume(param, params[2]);
                    ARRAY_STORAGE.update(resume);
                    printAll();
                    break;
                case "delete":
                    ARRAY_STORAGE.delete(param);
                    printAll();
                    break;
                case "get":
                    resume = ARRAY_STORAGE.get(param);
                    if (resume != null) {
                        System.out.println(resume);
                    }
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        List<Resume> all = ARRAY_STORAGE.getAllSorted();
        System.out.println("----------------------------");
        if (all.size() == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println("uuid: " + r.getUuid() + " - Full name: " + r.getFullName());
            }
        }
        System.out.println("----------------------------");
    }
}
