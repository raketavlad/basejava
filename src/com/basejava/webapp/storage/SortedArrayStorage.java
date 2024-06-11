package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage  extends AbstractArrayStorage {

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Хранилище переполнено");
        } else if (index >= 0) {
            System.out.println("ERROR: Такое резюме уже есть в хранилище!");
        } else {
            for (int i = size; i > -index - 1; i--) {
                storage[i] = storage[i - 1];
            }
            storage[-index - 1] = resume;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Резюме с uuid '" + uuid + "' отсутствует в хранилище!");
            return;
        }
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size- 1] = null;
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
