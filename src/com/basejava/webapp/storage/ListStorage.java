package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage<Integer> {

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
        return new ArrayList<>(storage);
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
    protected void doUpdate(Resume resume, Integer searchKey) {
        storage.set(searchKey, resume);
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        storage.add(-searchKey - 1, resume);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        storage.remove(searchKey.intValue());
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "Random Name");
        return Collections.binarySearch(storage, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }
}