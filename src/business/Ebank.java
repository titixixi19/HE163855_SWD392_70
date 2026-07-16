package business;

import data.Account;
import data.IAccountDAO;

/**
 * BUSINESS LOGIC LAYER
 * Ebank class (as required by the assignment guideline): the Facade that
 * orchestrates the login flow and delegates to the specialised services.
 *
 * SOLID:
 *  - SRP: only orchestrates/authenticates; captcha, validation, i18n and
 *         storage each live in their own service.
 *  - DIP: depends on abstractions (ILanguageService, IValidatorService,
 *         ICaptchaService, IAccountDAO) — all injected via the constructor.
 *         This class never uses `new` on a collaborator.
 */
public class Ebank implements IEbankService {

    private final ILanguageService  langService;
    private final IValidatorService validator;
    private final ICaptchaService   captchaService;
    private final IAccountDAO        accountDAO;

    public Ebank(ILanguageService langService,
                 IValidatorService validator,
                 ICaptchaService captchaService,
                 IAccountDAO accountDAO) {
        this.langService    = langService;
        this.validator      = validator;
        this.captchaService = captchaService;
        this.accountDAO     = accountDAO;
    }

    // ── Language ──────────────────────────────────────────────────────────────

    /** Function 1 (guideline): switch UI language to the given locate. */
    @Override
    public void setLocate(Locate locate) {
        langService.setLocate(locate);
    }

    @Override
    public String getMessage(String key) {
        return langService.getMessage(key);
    }

    // ── Validation delegates ──────────────────────────────────────────────────

    /** Returns a message about the value of the account number. */
    @Override
    public String checkAccountNumber(String accountNumber) {
        return validator.checkAccountNumber(accountNumber);
    }

    /** Returns a message about the value of the password. */
    @Override
    public String checkPassword(String password) {
        return validator.checkPassword(password);
    }

    /** Returns a message about the value of the captcha. */
    @Override
    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        return validator.checkCaptcha(captchaInput, captchaGenerate);
    }

    // ── Captcha ───────────────────────────────────────────────────────────────

    /** Generate a random captcha string (delegated to CaptchaService). */
    @Override
    public String generateCaptcha() {
        return captchaService.generateCaptcha();
    }

    // ── Login ─────────────────────────────────────────────────────────────────

    /**
     * Authenticate account number + password against data store.
     * Returns true if credentials match and account is ACTIVE.
     */
    @Override
    public boolean authenticate(String accountNumber, String password) {
        Account acc = accountDAO.findByAccountNumber(accountNumber);
        if (acc == null) return false;
        if (!acc.isActive()) return false;
        return acc.getPassword().equals(password);
    }

    /**
     * Write a login event to the activity log.
     */
    @Override
    public void logEvent(String accountNumber, String event) {
        accountDAO.logEvent(accountNumber, event);
    }
}
