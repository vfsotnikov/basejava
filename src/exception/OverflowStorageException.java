package exception;

public class OverflowStorageException extends StorageException {
    public OverflowStorageException(String uuid) {
        super("Storage overflow",uuid);
    }
}
