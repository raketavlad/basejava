package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).
            thenComparing(Resume::getUuid);

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getList();
        Collections.sort(list, RESUME_COMPARATOR);
        return list;
    }

    protected abstract List<Resume> getList();

    @Override
    public final void update(Resume resume) {
        SK searchKey = getNotExistingSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    @Override
    public final void save(Resume resume) {
        SK searchKey = getExistingSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    @Override
    public final Resume get(String uuid) {
        SK searchKey = getNotExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public final void delete(String uuid) {
        SK searchKey = getNotExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    private SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            return searchKey;
        }
        throw new ExistStorageException(uuid);
    }

    private SK getNotExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);
}
