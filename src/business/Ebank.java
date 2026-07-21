package business;

import data.Account;
import data.IAccountDAO;

public class Ebank implements IEbankService {

    private final ILanguageService langService;
    private final IValidatorService validator;
    private final ICaptchaService  captchaService;
    private final IAccountDAO accountDAO;

    public Ebank(ILanguageService langService,
                 IValidatorService validator,
                 ICaptchaService captchaService,
                 IAccountDAO accountDAO) {
        this.langService  = langService;
        this.validator = validator;
        this.captchaService = captchaService;
        this.accountDAO = accountDAO;
    }


    @Override
    public void setLocate(Locate locate) {
        langService.setLocate(locate);
    }

    @Override
    public String getMessage(String key) {
        return langService.getMessage(key);
    }

    @Override
    public String checkAccountNumber(String accountNumber) {
        return validator.checkAccountNumber(accountNumber);
    }


    @Override
    public String checkPassword(String password) {
        return validator.checkPassword(password);
    }

    @Override
    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        return validator.checkCaptcha(captchaInput, captchaGenerate);
    }


    @Override
    public String generateCaptcha() {
        return captchaService.generateCaptcha();
    }

    @Override
    public boolean authenticate(String accountNumber, String password) {
        Account acc = accountDAO.findByAccountNumber(accountNumber);
        if (acc == null) return false;
        if (!acc.isActive()) return false;
        return acc.getPassword().equals(password);
    }

    @Override
    public void logEvent(String accountNumber, String event) {
        accountDAO.logEvent(accountNumber, event);
    }
}
