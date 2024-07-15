package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MapStorage extends AbstractStorage {

    private final LinkedHashMap<Integer, Resume> storage = new LinkedHashMap<>();

    @Override
    public Resume[] getAll() {
        ArrayList<Resume> values = new ArrayList<>(storage.values());
        return values.toArray(Resume[]::new);
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
        int hashcode = (int) searchKey;
        storage.replace(hashcode, resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume.hashCode(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        int hashcode = (int) searchKey;
        return storage.get(hashcode);
    }

    @Override
    protected void doDelete(Object searchKey) {
        int hashcode = (int) searchKey;
        storage.remove(hashcode);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        if (storage.containsKey(uuid.hashCode())) {
            return uuid.hashCode();
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

}
