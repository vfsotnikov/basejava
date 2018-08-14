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

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    private static final Resume[] RESUMES = {RESUME_1, RESUME_2, RESUME_3};

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp()  {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clearResumeFromStorage() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void updateResumeInStorage() {
        Resume resume = new Resume(UUID_2);
        storage.update(resume);
        assertEquals(resume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateResumeWithNotExistStorageException() {
        Resume resume = new Resume("Test UUID");
        storage.update(resume);
    }

    @Test
    public void saveResumeInStorage() {
        storage.save(RESUME_4);
        assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveResumeWithExistStorageException() {
        storage.save(RESUME_3);
    }

    @Test
    public void saveResumeWithOverflowStorageException() {
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + (i + 1)));

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
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getResumeWithNotExistStorageException() {
        storage.get(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteResumeWithNotExistStorageException() {
        storage.delete(UUID_3);
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteResumeFromStorage() {
        storage.delete(UUID_4);
    }

    @Test
    public void getAllResumes() {
        assertArrayEquals(RESUMES, storage.getAll());
    }

    @Test
    public void sizeStorage() {
        assertEquals(3, storage.size());
    }
}