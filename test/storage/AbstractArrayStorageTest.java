package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.OverflowStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {

    private Storage storage;
    private Resume resume1 = new Resume("uuid1");
    private Resume resume2 = new Resume("uuid2");
    private Resume resume3 = new Resume("uuid3");
    private Resume resume4 = new Resume("uuid4");
    private Resume[] resumes = {resume1, resume2, resume3};

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void clearResumeFromStorage() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void updateResumeInStorage() {
        resume2 = new Resume("uuid2");
        storage.update(resume2);
        assertEquals(resume2, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateResumeWithNotExistStorageException() {
        resume2 = new Resume("Test UUID");
        storage.update(resume2);
    }

    @Test
    public void saveResumeInStorage() {
        storage.save(resume4);
        assertEquals(resume4, storage.get("uuid4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveResumeWithExistStorageException() {
        storage.save(resume3);
    }

    @Test
    public void saveResumeWithOverflowStorageException() {
        Resume resume;
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
                resume = new Resume("uuid" + (i + 1));
                storage.save(resume);

            }
        } catch (OverflowStorageException e) {

            int countStorage = storage.size();
            if (countStorage > STORAGE_LIMIT) {
                Assert.fail("Count elementes in storage   = " + countStorage);
            }
        }
    }

    @Test
    public void getResumeFromStorage() {
        assertEquals(resume1, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getResumeWithNotExistStorageException() {
        storage.get("uuid4");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteResumeWithNotExistStorageException() {
        storage.delete("uuid3");
        storage.get("uuid3");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteResumeFromStorage() {
        storage.delete("uuid4");
    }

    @Test
    public void getAllResumes() {
        assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void sizeStorage() {
        assertEquals(3, storage.size());
    }
}