import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int position = findPosition(r.uuid);
        if (size == storage.length) {
            System.out.println("Хранилище переполнено");
            return;
        }
        if (position == -1) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("Такое резюме уже есть!");
        }
    }

    public Resume get(String uuid) {
        int position = findPosition(uuid);
        return (position != -1) ? storage[position] : null;
    }

    public void delete(String uuid) {
        int position = findPosition(uuid);
        if (position == -1) {
            return;
        }
        int length = storage.length;
        if (position != length - 1) {
            System.arraycopy(storage, position + 1, storage, position, length - position - 1);
        } else {
            storage[position] = null;
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int getSize() {
        return size;
    }

    private int findPosition(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}