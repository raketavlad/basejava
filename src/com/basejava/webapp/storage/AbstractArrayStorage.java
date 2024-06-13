package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Резюме с uuid '" + uuid + "' отсутствует в хранилище!");
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Хранилище переполнено");
        } else if (index >= 0) {
            System.out.println("ERROR: Такое резюме уже есть в хранилище!");
        } else {
            saveDiffer(resume, index);
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Резюме с uuid '" + uuid + "' отсутствует в хранилище!");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Резюме с uuid '" + uuid + "' отсутствует в хранилище!");
            return;
        }
        deleteDiffer(index);
        storage[size- 1] = null;
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract void saveDiffer(Resume resume, int index);

    protected abstract void deleteDiffer(int index);

    protected abstract int getIndex(String uuid);
}
