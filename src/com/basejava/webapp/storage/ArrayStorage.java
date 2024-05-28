package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int position = findPosition(resume.getUuid());
        if (size == storage.length) {
            System.out.println("ERROR: Хранилище переполнено");
            return;
        }
        if (position == -1) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("ERROR: Такое резюме уже есть в хранилище!");
        }
    }

    public Resume get(String uuid) {
        int position = findPosition(uuid);
        if (position == -1) {
            System.out.println("ERROR: Резюме с uuid '" + uuid + "' отсутствует в хранилище!");
            return null;
        } else {
            return storage[position];
        }
    }

    public void delete(String uuid) {
        int position = findPosition(uuid);
        if (position == -1) {
            System.out.println("ERROR: Резюме с uuid '" + uuid + "' отсутствует в хранилище!");
            return;
        }
        if (position != storage.length - 1) {
            storage[position] = storage[size - 1];
        } else {
            storage[position] = null;
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int getSize() {
        return size;
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int position = findPosition(uuid);
        if (position == -1) {
            System.out.println("ERROR: Резюме с uuid '" + uuid + "' отсутствует в хранилище!");
        } else {
            storage[position] = resume;
        }
    }

    private int findPosition(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}