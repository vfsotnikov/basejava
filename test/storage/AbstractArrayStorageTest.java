package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.OverflowStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {

    private Storage storage;
    private Resume resume1 = new Resume("uuid1");
    private Resume resume2 = new Resume("uuid2");
    private Resume resume3 = new Resume("uuid3");
    private Resume resume4 = new Resume("dummy");

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
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void updateTest1() {
        resume2 = new Resume("uuid2");
        storage.update(resume2);
        Assert.assertTrue(resume2.equals(storage.get("uuid2")));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateTest2() {
        resume2 = new Resume("Test UUID");
        storage.update(resume2);
        Assert.assertEquals("Test UUID", storage.get("Test UUID"));
    }

    @Test
    public void saveTest1() {
        storage.save(resume4);
        Assert.assertTrue(resume4.equals(storage.get("dummy")));
    }

    @Test(expected = ExistStorageException.class)
    public void saveTest2() {
        storage.save(resume3);
    }

    @Test
    public void saveTest3() {
        try {
            while (true) {
                int size = storage.size();
                resume3 = new Resume("uuid" + (size+1));
                storage.save(resume3);
                if (storage.size() > STORAGE_LIMIT) {
                    Assert.fail("Storage size = "+storage.size());
                }
            }
        } catch (OverflowStorageException e) {
            Assert.assertTrue(storage.size() == STORAGE_LIMIT);
        }
    }

    @Test
    public void getTest1() {
        Assert.assertEquals(resume1, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getTest2() {
        Assert.assertEquals(resume1, storage.get("dummy"));
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
        storage.delete("dummy");
    }

    @Test
    public void getAllTest() {
        Resume[] storageGetAll = storage.getAll();
        Assert.assertTrue(storageGetAll.length==storage.size());
    }

    @Test
    public void sizeTest() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void getIndex (){

    }

    @Test
    public void insertElement (){

    }

    @Test
    public void fillDeletedElement (){

    }

}