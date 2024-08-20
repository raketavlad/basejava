package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doSave(Resume resume, Integer index) {
        storageOverflow(resume);
        storage[size] = resume;
        size++;
    }

    @Override
    protected void doDelete(Integer index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}