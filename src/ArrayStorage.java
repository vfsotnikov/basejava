import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    int indexArrayResume = 0;

    void clear() {
        Arrays.fill(storage, 0, indexArrayResume - 1, null);
        indexArrayResume = 0;
    }

    void save(Resume r) {
        storage[indexArrayResume] = r;
        indexArrayResume++;
    }

    Resume get(String uuid) {
        int resultSearch = search(uuid);
        if (resultSearch >= 0) {
            return storage[resultSearch];
        }

        return null;
    }

    void delete(String uuid) {
//        Resume[] storageCopy = Arrays.copyOfRange(storage, 0, indexArrayResume);
        int searchUuid = search(uuid);
        System.arraycopy(storage,searchUuid+1,storage,searchUuid,indexArrayResume-1);
//        if (searchUuid >= 0) {
//            for (int i = searchUuid; i < indexArrayResume - 1; i++) {
//                storage[i] = storageCopy[i + 1];
//
//            }
            indexArrayResume--;
//        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, indexArrayResume);

    }

    int size() {
        return indexArrayResume;
    }

    private int search(String uuid) {
        if (!uuid.isEmpty()) {
            for (int i = 0; i < indexArrayResume; i++) {

                if (storage[i].uuid==uuid) {
                    return i;
                }
            }
        }
        return -1;
    }

}