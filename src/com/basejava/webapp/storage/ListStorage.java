package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new LinkedList<Resume>();

    @Override
    public Resume[] getAll() {
        return storage.toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void updateResume(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        storage.add(-index - 1, resume);
    }

    @Override
    protected Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    protected void deleteResume(int index) {
        storage.remove(index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Collections.binarySearch(storage, searchKey);
    }

    @Override
    protected void storageOverflow(Resume resume) {}
}