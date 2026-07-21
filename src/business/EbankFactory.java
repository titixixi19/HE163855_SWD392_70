package business;

import data.AccountDAO;
import data.FileHelper;
import data.IAccountDAO;
import data.IAccountStorage;
import data.IMessageStorage;
import data.MessageFileHelper;

public class EbankFactory {

    private EbankFactory() {
    }

    public static IEbankService create() {
        IMessageStorage   messageStorage = new MessageFileHelper();
        IAccountStorage   accountStorage = new FileHelper();

        ILanguageService  langService = new LanguageService(messageStorage);
        IValidatorService validator   = new ValidatorService(langService);
        ICaptchaService   captcha     = new CaptchaService();
        IAccountDAO       accountDAO  = new AccountDAO(accountStorage);

        return new Ebank(langService, validator, captcha, accountDAO);
    }
}
