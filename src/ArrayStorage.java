import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    private int indexNextResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, indexNextResume, null);
        indexNextResume = 0;
    }

    public void update(Resume r) {
        int posResume = search(r.uuid, true);
        if (posResume >= 0) storage[posResume] = r;
    }

    public void save(Resume r) {
        int posResume = search(r.uuid, false);
        if (posResume == -1 && indexNextResume != storage.length) {
            storage[indexNextResume] = r;
            indexNextResume++;
        } else {
            System.out.println("Массив резюме заполнен, добавление не возможно!");
        }

    }

    public Resume get(String uuid) {
        int resultSearch = search(uuid, true);
        if (resultSearch >= 0) {
            return storage[resultSearch];
        }
        return null;
    }

    public void delete(String uuid) {
        int resultSearch = search(uuid, true);
        if (resultSearch >= 0) {
            System.arraycopy(storage, resultSearch + 1, storage, resultSearch, indexNextResume - 1 - resultSearch);
            indexNextResume--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, indexNextResume);
    }

    public int size() {
        return indexNextResume;
    }

    private int search(String uuid, boolean alarm) {
        if (!uuid.isEmpty()) {
            for (int i = 0; i < indexNextResume; i++) {

                if (storage[i].uuid == uuid) {
                    return i;
                }
            }
        }
        if (alarm) System.out.println("Резюме не найдено в базе!");
        return -1;
    }

}