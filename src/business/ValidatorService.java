package business;

public class ValidatorService implements IValidatorService {

    private static final String ACCOUNT_REGEX  = "^\\d{10}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d).{6,}$";

    private final ILanguageService lang;

    public ValidatorService(ILanguageService lang) {
        this.lang = lang;
    }

    
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

    
    @Override
    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        if (captchaInput == null || captchaInput.trim().isEmpty()) {
            return lang.getMessage("captcha.empty");
        }
        if (captchaGenerate == null || captchaGenerate.isEmpty()) {
            return lang.getMessage("captcha.invalid");
        }
        for (char c : captchaInput.toCharArray()) {
            if (!captchaGenerate.contains(String.valueOf(c))) {
                return lang.getMessage("captcha.invalid");
            }
        }
        return lang.getMessage("captcha.valid");
    }
}
