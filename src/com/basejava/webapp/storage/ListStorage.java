package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new LinkedList<>();

    @Override
    public Resume[] getAll() {
        return storage.toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        storage.set(index, resume);
    }

    @Override
    protected void doSave(Resume resume, int index) {
        storage.add(-index - 1, resume);
    }

    @Override
    protected Resume doGet(int index) {
        return storage.get(index);
    }

    @Override
    protected void doDelete(int index) {
        storage.remove(index);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Collections.binarySearch(storage, searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        if ((int) searchKey < 0) {
            return false;
        }
        return true;
    }
}