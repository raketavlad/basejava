package com.basejava.webapp.storage;

import com.basejava.webapp.storage.serializationstrategy.XmlStreamSerializer;

public class XmlPathStorageTest extends  AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}
