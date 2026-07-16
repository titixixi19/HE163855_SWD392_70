package data;

import java.io.IOException;
import java.util.List;

/**
 * DATA ACCESS LAYER — abstraction for the physical account storage.
 * AccountDAO depends on this interface, not the concrete FileHelper (DIP),
 * so the storage backend (CSV, DB, ...) can be swapped without touching the DAO.
 */
public interface IAccountStorage {

    List<Account> loadAccounts() throws IOException;

    Account findByAccountNumber(String accountNumber) throws IOException;

    void writeLog(String accountNumber, String event);
}
