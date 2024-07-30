package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MapResumeStorage extends AbstractStorage {

    private final LinkedHashMap<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected List<Resume> getList() {
        List<Resume> listResume = new ArrayList<>(storage.size());
        for (String key : storage.keySet()) {
            listResume.add(storage.get(key));
        }
        return listResume;
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
        storage.put(((Resume) searchKey).getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get(((Resume) searchKey).getUuid());
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "Random name");
        return searchKey;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsValue(searchKey);
    }
}
