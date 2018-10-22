package storage;

import exception.ExistStorageException;
import exception.OverflowStorageException;
import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 100;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;


    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
        size = 0;

    }

    protected void updateResumeIntoStorage(Resume resume, int index) {
        storage[index] = resume;
    }

    public void saveResumeIntoStorage(Resume resume, int index) {
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            throw new OverflowStorageException(resume.getUuid());
        } else {
            insertElement(resume, index);
            size++;
        }
    }

    protected Resume getResumeFromStorage(int index) {
        return storage[index];
    }

    protected void deleteResumeFromStorage(int index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    protected Resume[] getAllResumeFromStorage(){
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected int sizeOfStorage() {
        return size;
    }

    protected abstract void fillDeletedElement(int index);

}