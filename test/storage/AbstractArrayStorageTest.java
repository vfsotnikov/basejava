package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.OverflowStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {

    private Storage storage;
    private Resume resume1 = new Resume("uuid1");
    private Resume resume2 = new Resume("uuid2");
    private Resume resume3 = new Resume("uuid3");
    private Resume resume4 = new Resume("uuid4");

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
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        resume2 = new Resume("uuid2");
        storage.update(resume2);
        assertEquals(resume2, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistStorageException() {
        resume2 = new Resume("Test UUID");
        storage.update(resume2);
    }

    @Test
    public void save() {
        storage.save(resume4);
        assertEquals(resume4, storage.get("uuid4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistStorageException() {
        storage.save(resume3);
    }

    @Test
    public void saveOverflowStorageException() {
        Resume resume;
        try {
            for (int i = storage.size(); i<STORAGE_LIMIT; i++){
                resume = new Resume("uuid" + (i + 1));
                storage.save(resume);
                if (storage.size() > STORAGE_LIMIT) {
                    Assert.fail("Storage size = " + i);
                }
            }
        } catch (OverflowStorageException e) {
            assertEquals(storage.size(), STORAGE_LIMIT);
        }
    }

    @Test
    public void getTest1() {
        assertEquals(resume1, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getTest2() {
        assertEquals(resume1, storage.get("uuid4"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteTest1() {
        storage.delete("uuid3");
        storage.get("uuid3");
        Assert.fail("Resume not deleted");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteTest2() {
        storage.delete("uuid4");
    }

    @Test
    public void getAllTest() {
        Resume[] resumes = storage.getAll();
        assertEquals(3, resumes.length);
        for (int i=0;i<storage.size();){
            assertEquals(resumes[i],storage.get("uuid"+(++i)));
        }
    }

    @Test
    public void sizeTest() {
        assertEquals(3, storage.size());
    }
}