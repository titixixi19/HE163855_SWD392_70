package data;

/**
 * Entity class representing a bank account.
 * Used as a data transfer object between layers.
 */
public class Account {

    private String accountNumber;
    private String password;
    private String status; // ACTIVE | LOCKED

    public Account(String accountNumber, String password, String status) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.status = status;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getPassword()      { return password; }
    public String getStatus()        { return status; }

    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(status);
    }

    @Override
    public String toString() {
        return "Account{accountNumber='" + accountNumber + "', status='" + status + "'}";
    }
}
