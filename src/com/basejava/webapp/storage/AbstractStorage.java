package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected Storage storage;
    protected Object searchKey;

    @Override
    public final void update(Resume resume) {
        searchKey = getNotExistingSearchKey(resume.getUuid());
        doUpdate(resume, (int) searchKey);
    }

    @Override
    public final void save(Resume resume) {
        searchKey = getExistingSearchKey(resume.getUuid());
        doSave(resume, (int) searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        searchKey = getNotExistingSearchKey(uuid);
        return doGet((int) searchKey);
    }

    @Override
    public final void delete(String uuid) {
        searchKey = getNotExistingSearchKey(uuid);
        doDelete((int) searchKey);
    }

    private int getExistingSearchKey(String uuid) {
        searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            return (int) searchKey;
        }
        throw new ExistStorageException(uuid);
    }

    private int getNotExistingSearchKey(String uuid) {
        searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return (int) searchKey;
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doSave(Resume resume, int index);

    protected abstract Resume doGet(int index);

    protected abstract void doDelete(int index);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);
}
