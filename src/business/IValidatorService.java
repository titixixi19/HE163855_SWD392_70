package business;


public interface IValidatorService {

    String checkAccountNumber(String accountNumber);

    String checkPassword(String password);

    String checkCaptcha(String captchaInput, String captchaGenerate);
}
