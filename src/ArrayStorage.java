import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    int indexNextResume = 0;

    void clear() {
        Arrays.fill(storage, 0, indexNextResume, null);
        indexNextResume = 0;
    }

    void save(Resume r) {
        storage[indexNextResume] = r;
        indexNextResume++;
    }

    Resume get(String uuid) {
        int resultSearch = search(uuid);
        if (resultSearch >= 0) {
            return storage[resultSearch];
        }

        return null;
    }

    void delete(String uuid) {
        int resultSearch = search(uuid);
        if (resultSearch >= 0) {
            System.arraycopy(storage, resultSearch + 1, storage, resultSearch, indexNextResume - 1);
            indexNextResume--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, indexNextResume);

    }

    int size() {
        return indexNextResume;
    }

    private int search(String uuid) {
        if (!uuid.isEmpty()) {
            for (int i = 0; i < indexNextResume; i++) {

                if (storage[i].uuid == uuid) {
                    return i;
                }
            }
        }
        return -1;
    }

}