package business;

public interface IEbankService {

    void setLocate(Locate locate);

    String getMessage(String key);

    String checkAccountNumber(String accountNumber);

    String checkPassword(String password);

    String checkCaptcha(String captchaInput, String captchaGenerate);

    String generateCaptcha();

    boolean authenticate(String accountNumber, String password);

    void logEvent(String accountNumber, String event);
}
