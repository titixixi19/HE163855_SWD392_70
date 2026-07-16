package business;

/**
 * BUSINESS LOGIC LAYER — abstraction for input validation.
 * Ebank depends on this interface, not the concrete ValidatorService (DIP).
 */
public interface IValidatorService {

    /** Returns a message about the value of the account number. */
    String checkAccountNumber(String accountNumber);

    /** Returns a message about the value of the password. */
    String checkPassword(String password);

    /** Returns a message about the value of the captcha. */
    String checkCaptcha(String captchaInput, String captchaGenerate);
}
