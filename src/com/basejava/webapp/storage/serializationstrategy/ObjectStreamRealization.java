package com.basejava.webapp.storage.serializationstrategy;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.*;

public class ObjectStreamRealization implements SaveAndReadStrategy {

    @Override
    public void save(Resume resume, String fileName) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream((fileName))));
            oos.writeObject(resume);
            oos.close();
        } catch (IOException e) {
            throw new StorageException("Write error! File not found!", null, e);
        }
    }

    @Override
    public Resume read(String fileName) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            Resume resume = (Resume) ois.readObject();
            ois.close();
            return resume;
        } catch (IOException | ClassNotFoundException e) {
            throw new StorageException("Read error!", null, e);
        }
    }
}
