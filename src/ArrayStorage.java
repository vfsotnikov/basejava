import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    Resume r = new Resume("Данное резюме отсутсвует в базе");

    int index = 0;

    void clear() {
        Arrays.fill(storage, null);
        index = 0;
    }

    void save(Resume r) {
        storage[index] = r;
        index++;
    }

    Resume get(String uuid) {
        int resultSearch = search(uuid);
        if (resultSearch >= 0) {
            return storage[resultSearch];
        }

        return r;
    }

    void delete(String uuid) {
        Resume[] storageCopy = Arrays.copyOfRange(storage, 0, index);
        for (int i = search(uuid); i < index - 1; i++) {
            storage[i] = storageCopy[i + 1];

        }
        index--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        if (index == 0) {
            Resume[] storageNull = new Resume[1];
            storageNull[0] = new Resume("Массив пуст!");
            return storageNull;
        }

        return Arrays.copyOfRange(storage, 0, index);
    }

    int size() {
        return index;
    }

    private int search(String uuid) {
        if (!uuid.isEmpty()) {
            for (int i = 0; i < index; i++) {

                if (storage[i].equals(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }

}