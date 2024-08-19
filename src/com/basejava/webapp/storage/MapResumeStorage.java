package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected List<Resume> getList() {
        return Arrays.asList(storage.values().toArray(new Resume[0]));
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
        storage.replace(((Resume) searchKey).getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
