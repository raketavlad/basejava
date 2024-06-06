package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: Резюме с uuid '" + uuid + "' отсутствует в хранилище!");
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Хранилище переполнено");
        } else if (getIndex(resume.getUuid()) != -1) {
            System.out.println("ERROR: Такое резюме уже есть в хранилище!");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: Резюме с uuid '" + uuid + "' отсутствует в хранилище!");
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}