package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.serializationstrategy.SaveAndReadStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private final Path directory;
    private SaveAndReadStrategy saveAndReadStrategy;

    protected PathStorage(String dir, SaveAndReadStrategy saveAndReadStrategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        Objects.requireNonNull(saveAndReadStrategy, "Save strategy must not be null");
        this.saveAndReadStrategy = saveAndReadStrategy;
    }

    @Override
    protected List<Resume> getList() {
        return getListFiles().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        saveAndReadStrategy.save(resume, path.toString());
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        saveAndReadStrategy.save(resume, path.toString());
    }

    @Override
    protected Resume doGet(Path path) {
        return saveAndReadStrategy.read(path.toString());
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new StorageException("Path not found", path.toString());
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    public void clear() {
        getListFiles().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getListFiles().count();
    }

    public Stream<Path> getListFiles() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Pathname does not denote a directory");
        }
    }
}
