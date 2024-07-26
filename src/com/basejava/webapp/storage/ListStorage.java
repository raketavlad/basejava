package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {

    private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume resume1, Resume resume2) {
            return resume1.getUuid().compareTo(resume2.getUuid());
        }
    }

    private static final ResumeComparator RESUME_COMPARATOR = new ResumeComparator();

    private final List<Resume> storage = new LinkedList<>();

    @Override
    protected List<Resume> getList() {
        return storage;
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
    protected void doUpdate(Resume resume, Object searchKey) {
        int index = (int) searchKey;
        storage.set(index, resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        int index = (int) searchKey;
        storage.add(-index - 1, resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        int index = (int) searchKey;
        return storage.get(index);
    }

    @Override
    protected void doDelete(Object searchKey) {
        int index = (int) searchKey;
        storage.remove(index);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "Random Name");
        return Collections.binarySearch(storage, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }
}