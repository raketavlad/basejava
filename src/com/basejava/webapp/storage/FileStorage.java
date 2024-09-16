package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.serializationstrategy.SaveAndReadStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    private final File directory;
    private SaveAndReadStrategy saveAndReadStrategy;

    protected FileStorage(File directory, SaveAndReadStrategy saveAndReadStrategy) {
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        Objects.requireNonNull(saveAndReadStrategy, "Save strategy must not be null");
        this.saveAndReadStrategy = saveAndReadStrategy;
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> listResume = new ArrayList<>();
        for (File file : createListFiles()) {
            Resume resume = doGet(file);
            listResume.add(resume);
        }
        return listResume;
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        saveAndReadStrategy.save(resume, file.toString());
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        saveAndReadStrategy.save(resume, file.toString());
    }

    @Override
    protected Resume doGet(File file) {
        return saveAndReadStrategy.read(file.getAbsolutePath());
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File not found", file.getName());
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        for (File file : createListFiles()) {
            file.delete();
        }
    }

    @Override
    public int size() {
        return createListFiles().length;
    }

    private File[] createListFiles() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Pathname does not denote a directory");
        }
        return files;
    }
}
