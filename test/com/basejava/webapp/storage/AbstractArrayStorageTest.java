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
        Assertions.assertTrue(assertSize(0));
        ArrayStorage emptyArrayStorage = new ArrayStorage();
        Assertions.assertArrayEquals(storage.getAll(), emptyArrayStorage.getAll());
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
        storage.save(RESUME_4);
        Assertions.assertTrue(assertGet(RESUME_4));
        Assertions.assertTrue(assertSize(4));
    }

    @Test
    public void get() {
        Assertions.assertTrue(assertGet(RESUME_1));
        Assertions.assertTrue(assertGet(RESUME_2));
        Assertions.assertTrue(assertGet(RESUME_3));
    }

    @Test
    public void delete() {
        Assertions.assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
        storage.delete(RESUME_1.getUuid());
        Assertions.assertTrue(assertSize(2));
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
        Assertions.assertTrue(assertSize(3));
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
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
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

    private boolean assertSize(int size) {
        return size == storage.size();
    }

    private boolean assertGet(Resume resume) {
        return resume.equals(storage.get(resume.getUuid()));
    }
}