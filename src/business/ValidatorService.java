package business;

/**
 * BUSINESS LOGIC LAYER
 * All validation logic: account number, password, captcha.
 * Returns localised message strings via LanguageService.
 * Each check returns a message about the value (valid or the specific error),
 * as required by the assignment ("Return value: messages about the value").
 */
public class ValidatorService implements IValidatorService {

    private static final String ACCOUNT_REGEX  = "^\\d{10}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d).{6,}$";

    private final ILanguageService lang;

    public ValidatorService(ILanguageService lang) {
        this.lang = lang;
    }

    /**
     * Validate account number: must be exactly 10 digits.
     * Returns the "account.valid" message if valid, an error message if not.
     */
    @Override
    public String checkAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            return lang.getMessage("account.empty");
        }
        if (!accountNumber.matches(ACCOUNT_REGEX)) {
            return lang.getMessage("account.invalid");
        }
        return lang.getMessage("account.valid");
    }

    /**
     * Validate password: >= 6 chars, contains letters AND digits.
     * Returns the "password.valid" message if valid, an error message if not.
     */
    @Override
    public String checkPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return lang.getMessage("password.empty");
        }
        if (!password.matches(PASSWORD_REGEX)) {
            return lang.getMessage("password.invalid");
        }
        return lang.getMessage("password.valid");
    }

    /**
     * Validate captcha: every char entered must appear in the generated captcha.
     * Returns the "captcha.valid" message if valid, an error message if not.
     */
    @Override
    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        if (captchaInput == null || captchaInput.trim().isEmpty()) {
            return lang.getMessage("captcha.empty");
        }
        if (captchaGenerate == null || captchaGenerate.isEmpty()) {
            return lang.getMessage("captcha.invalid");
        }
        // Check each character of input exists in the generated captcha
        for (char c : captchaInput.toCharArray()) {
            if (!captchaGenerate.contains(String.valueOf(c))) {
                return lang.getMessage("captcha.invalid");
            }
        }
        return lang.getMessage("captcha.valid");
    }
}
