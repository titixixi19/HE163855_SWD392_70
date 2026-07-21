package data;

public interface IAccountDAO {

    
    Account findByAccountNumber(String accountNumber);

    
    void logEvent(String accountNumber, String event);
}
