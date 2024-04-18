import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, size(), null);
    }

    void save(Resume r) {
        boolean hasResume = false;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                hasResume = true;
                break;
            }
        }
        if (!hasResume) {
            storage[size()] = r;
        }
    }

    Resume get(String uuid) {
        int resumeNumber = number(uuid);
        if (resumeNumber == -1) {
            return null;
        }
        return storage[resumeNumber];
    }

    void delete(String uuid) {
        int resumeNumber = number(uuid);
        if (resumeNumber == -1) {
            return;
        }
        System.arraycopy(storage, resumeNumber + 1, storage, resumeNumber, size() - resumeNumber);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int size = 0;
        while (storage[size] != null) {
            size++;
        }
        return size;
    }

    private int number(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
