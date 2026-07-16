package data;

import java.io.IOException;

/**
 * DATA ACCESS LAYER
 * DAO (Data Access Object) for Account.
 * Sits between business layer and the storage backend.
 * Business layer only calls the DAO — never the storage directly.
 *
 * SOLID: depends on the IAccountStorage abstraction (DIP), injected via
 * the constructor, so the file backend can be swapped for a DB with no change here.
 */
public class AccountDAO implements IAccountDAO {

    private final IAccountStorage storage;

    public AccountDAO(IAccountStorage storage) {
        this.storage = storage;
    }

    /**
     * Find account by account number.
     * Returns null if not found or on I/O error.
     */
    @Override
    public Account findByAccountNumber(String accountNumber) {
        try {
            return storage.findByAccountNumber(accountNumber);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Log a login event.
     */
    @Override
    public void logEvent(String accountNumber, String event) {
        storage.writeLog(accountNumber, event);
    }
}
