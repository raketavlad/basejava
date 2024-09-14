package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serializationstrategy.ObjectStreamRealization;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamRealization()));
    }
}
