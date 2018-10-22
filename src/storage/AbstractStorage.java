package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void clear() {
        clearStorage();
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResumeIntoStorage(resume, index);
        }

    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResumeIntoStorage(resume, index);
        }

    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResumeFromStorage(index);
    }


    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResumeFromStorage(index);
        }
    }

    @Override
    public Resume[] getAll() {
        return getAllResumeFromStorage();
    }

    @Override
    public int size() {
        return sizeOfStorage();
    }

    protected abstract void clearStorage();

    protected abstract int getIndex(String uuid);

    protected abstract void updateResumeIntoStorage(Resume resume, int index);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void saveResumeIntoStorage(Resume resume, int index);

    protected abstract Resume getResumeFromStorage(int index);

    protected abstract void deleteResumeFromStorage(int index);

    protected abstract Resume[] getAllResumeFromStorage();

    protected abstract int sizeOfStorage();
}
