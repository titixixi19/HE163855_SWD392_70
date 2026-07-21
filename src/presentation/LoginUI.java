package presentation;

import business.IEbankService;

import java.util.Scanner;

public class LoginUI {

    private final IEbankService service;
    private final Scanner       scanner;

    public LoginUI(IEbankService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    
    public void startLogin() {
        String accountNumber = promptAccountNumber();

        String password = promptPassword();

        String captcha = service.generateCaptcha();
        System.out.println(service.getMessage("login.captchaGenerated") + " " + captcha);

        String captchaInput = promptCaptcha(captcha);
        if (captchaInput == null) {
            service.logEvent(accountNumber, "CAPTCHA_FAILED");
            return;
        }

        if (service.authenticate(accountNumber, password)) {
            System.out.println(service.getMessage("login.success"));
            service.logEvent(accountNumber, "LOGIN_SUCCESS");
        } else {
            System.out.println(service.getMessage("login.failed"));
            service.logEvent(accountNumber, "LOGIN_FAILED");
        }
    }

    private String promptAccountNumber() {
        while (true) {
            System.out.print(service.getMessage("login.enterAccount") + " ");
            String input = scanner.nextLine().trim();
            String message = service.checkAccountNumber(input);
            if (message.equals(service.getMessage("account.valid"))) return input;
            System.out.println(message);
        }
    }

    private String promptPassword() {
        while (true) {
            System.out.print(service.getMessage("login.enterPassword") + " ");
            String input = scanner.nextLine();
            String message = service.checkPassword(input);
            if (message.equals(service.getMessage("password.valid"))) return input;
            System.out.println(message);
        }
    }

    
    private String promptCaptcha(String generatedCaptcha) {
        System.out.print(service.getMessage("login.enterCaptcha") + " ");
        String input = scanner.nextLine().trim();
        String message = service.checkCaptcha(input, generatedCaptcha);
        if (message.equals(service.getMessage("captcha.valid"))) return input;
        System.out.println(message);
        return null;
    }
}
