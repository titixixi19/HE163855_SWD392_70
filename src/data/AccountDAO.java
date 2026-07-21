package data;

import java.io.IOException;

public class AccountDAO implements IAccountDAO {

    private final IAccountStorage storage;

    public AccountDAO(IAccountStorage storage) {
        this.storage = storage;
    }

    
    @Override
    public Account findByAccountNumber(String accountNumber) {
        try {
            return storage.findByAccountNumber(accountNumber);
        } catch (IOException e) {
            return null;
        }
    }

    
    @Override
    public void logEvent(String accountNumber, String event) {
        storage.writeLog(accountNumber, event);
    }
}
