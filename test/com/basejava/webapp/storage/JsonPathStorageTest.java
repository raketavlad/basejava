package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serializationstrategy.JsonStreamSerializer;

public class JsonPathStorageTest extends  AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
