package data;

import java.io.IOException;
import java.util.List;

public interface IAccountStorage {

    List<Account> loadAccounts() throws IOException;

    Account findByAccountNumber(String accountNumber) throws IOException;

    void writeLog(String accountNumber, String event);
}
