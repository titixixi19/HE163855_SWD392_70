package business;

/**
 * BUSINESS LOGIC LAYER — Facade abstraction.
 * The Presentation layer (LoginUI, Main) depends on this interface, not the
 * concrete Ebank class (DIP). Ebank is the single entry point that shields
 * the presentation layer from the business subsystem (Facade pattern).
 */
public interface IEbankService {

    /** Function 1 (guideline): switch UI language to the given locate. */
    void setLocate(Locate locate);

    String getMessage(String key);

    String checkAccountNumber(String accountNumber);

    String checkPassword(String password);

    String checkCaptcha(String captchaInput, String captchaGenerate);

    String generateCaptcha();

    /** Authenticate account number + password against the data store. */
    boolean authenticate(String accountNumber, String password);

    /** Write a login event to the activity log. */
    void logEvent(String accountNumber, String event);
}
