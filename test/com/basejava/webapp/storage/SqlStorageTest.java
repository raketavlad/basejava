package com.basejava.webapp.storage;

import com.basejava.webapp.Config;
import com.basejava.webapp.exception.StorageException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUser(),
                Config.get().getDbPassword()));
    }

    @Override
    public void updateNotExist() {

    }

    @Test
    @Override
    public void saveExist() {
        Assertions.assertThrows(StorageException.class, () -> {
            storage.save(RESUME_1);
        });
    }

    @Override
    public void deleteNotExist() {

    }
}
