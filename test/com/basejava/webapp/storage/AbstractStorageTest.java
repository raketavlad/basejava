package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorageTest {

    protected final Storage storage;

    public static final String UUID_1 = "uuid1";
    public static final String FULLNAME_1 = "First Resume";
    public static final String UUID_2 = "uuid2";
    public static final String FULLNAME_2 = "Second Resume";
    public static final String UUID_3 = "uuid3";
    public static final String FULLNAME_3 = "Third Resume";
    public static final String UUID_4 = "uuid4";
    public static final String FULLNAME_4 = "Fourth Resume";
    public static final String TEST_UUID = "dummy";

    public static final Resume RESUME_1;
    public static final Resume RESUME_2;
    public static final Resume RESUME_3;
    public static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, FULLNAME_1);
        RESUME_2 = new Resume(UUID_2, FULLNAME_2);
        RESUME_3 = new Resume(UUID_3, FULLNAME_3);
        RESUME_4 = new Resume(UUID_4, FULLNAME_4);
    }

    public AbstractStorageTest(Storage storage) {
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
        assertSize(0);
        Assertions.assertArrayEquals(storage.getAllSorted().toArray(new Resume[0]), new Resume[0]);
    }

    @Test
    public void update() {
        Resume firstResume = storage.get(UUID_1);
        storage.update(new Resume(UUID_1, "Random Name"));
        Resume secondResume = storage.get(UUID_1);
        Assertions.assertNotSame(firstResume, secondResume);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void delete() {
        Assertions.assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
        storage.delete(RESUME_1.getUuid());
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(RESUME_1.getUuid());
        });
    }

    @Test
    public void getAll() {
        List<Resume> testStorage = new ArrayList<>();
        testStorage.add(RESUME_1);
        testStorage.add(RESUME_2);
        testStorage.add(RESUME_3);
        Assertions.assertArrayEquals(testStorage.toArray(), storage.getAllSorted().toArray());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(RESUME_4);
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
            storage.get(TEST_UUID);
        });
    }

    @Test
    public void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete(TEST_UUID);
        });
    }

    private void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }
}