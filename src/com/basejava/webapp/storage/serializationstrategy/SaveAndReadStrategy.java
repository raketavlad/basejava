package com.basejava.webapp.storage.serializationstrategy;

import com.basejava.webapp.model.Resume;

public interface SaveAndReadStrategy {

    void save(Resume resume, String fileName);

    Resume read(String fileName);
}
