package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage  extends AbstractArrayStorage {

    @Override
    protected void saveDiffer(Resume resume, int index) {
        for (int i = size; i > -index - 1; i--) {
            storage[i] = storage[i - 1];
        }
        storage[-index - 1] = resume;
    }

    @Override
    protected void deleteDiffer(int index) {
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
