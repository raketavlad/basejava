package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class AbstractArrayStorageTest {

    private Storage storage;

    public static final String UUID_1 = "uuid1";
    public static final Resume RESUME_1 = new Resume(UUID_1);
    public static final String UUID_2 = "uuid2";
    public static final Resume RESUME_2 = new Resume(UUID_2);
    public static final String UUID_3 = "uuid3";
    public static final Resume RESUME_3 = new Resume(UUID_3);
    public static final String UUID_4 = "uuid4";
    public static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume firstResume = storage.get("uuid1");
        storage.update(new Resume("uuid1"));
        Resume secondResume = storage.get("uuid1");
        Assertions.assertNotSame(firstResume, secondResume);
    }

    @Test
    public void save() {
        storage.clear();
        storage.save(RESUME_1);
        Assertions.assertEquals(RESUME_1, storage.get(UUID_1));
        Assertions.assertEquals(1, storage.size());
    }

    @Test
    public void get() {
        Assertions.assertEquals(RESUME_1, storage.get(UUID_1));
        Assertions.assertEquals(RESUME_2, storage.get(UUID_2));
        Assertions.assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test
    public void delete() {
        Assertions.assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
        storage.delete(RESUME_1.getUuid());
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(RESUME_1.getUuid());
        });
    }

    @Test
    public void getAll() {
        Assertions.assertArrayEquals(new Resume[] {RESUME_1, RESUME_2, RESUME_3}, storage.getAll());
    }

    @Test
    public void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    public void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(RESUME_4);
        });
    }

    @Test
    public void saveOverFlow() {
        try {
            while (storage.size() != 10000) {
                storage.save(new Resume());
            }
        } catch (StorageException excepted) {
            Assertions.fail("Storage переполнен раньше времени");
        }
        Assertions.assertThrows(StorageException.class, () -> {
            storage.save(new Resume());
        });
    }

    @Test
    public void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(RESUME_1);
        });
    }

    @Test
    public void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }

    @Test
    public void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete("dummy");
        });
    }
}