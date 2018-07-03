package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    // погуглить для вставки Arrays.binarySearch
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Resume resume, int index) {
//        https://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = resume;
    }

    @Override
    protected void fillDeletedElement(int index) {
        int countMoved = size - index - 1;
        if (countMoved > 0) {
            System.arraycopy(storage, index+1, storage, index, countMoved);
        }
    }
}
