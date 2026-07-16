package data;

/**
 * DATA ACCESS LAYER — Data Access Object abstraction for Account.
 * The business layer depends on this interface, not the concrete AccountDAO (DIP).
 */
public interface IAccountDAO {

    /** Find account by account number; null if not found. */
    Account findByAccountNumber(String accountNumber);

    /** Log a login event. */
    void logEvent(String accountNumber, String event);
}
