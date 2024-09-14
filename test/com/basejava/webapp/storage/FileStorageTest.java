package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serializationstrategy.ObjectStreamRealization;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamRealization()));
    }
}
