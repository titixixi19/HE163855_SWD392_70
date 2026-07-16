package presentation;

import business.IEbankService;

import java.util.Scanner;

/**
 * PRESENTATION LAYER
 * Handles all user interaction for the login process.
 * Depends on the IEbankService abstraction (BLL Facade) — never the data
 * layer directly, and never the concrete Ebank class (DIP).
 */
public class LoginUI {

    private final IEbankService service;
    private final Scanner       scanner;

    public LoginUI(IEbankService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    /**
     * Run the full login flow:
     * 1. Account number (loop until valid format)
     * 2. Password       (loop until valid format)
     * 3. Captcha        (one attempt per generated captcha)
     */
    public void startLogin() {
        // Step 1: Account number
        String accountNumber = promptAccountNumber();

        // Step 2: Password
        String password = promptPassword();

        // Step 3: Captcha
        String captcha = service.generateCaptcha();
        System.out.println(service.getMessage("login.captchaGenerated") + " " + captcha);

        String captchaInput = promptCaptcha(captcha);
        if (captchaInput == null) {
            // captcha failed — already printed error
            service.logEvent(accountNumber, "CAPTCHA_FAILED");
            return;
        }

        // Authenticate
        if (service.authenticate(accountNumber, password)) {
            System.out.println(service.getMessage("login.success"));
            service.logEvent(accountNumber, "LOGIN_SUCCESS");
        } else {
            System.out.println(service.getMessage("login.failed"));
            service.logEvent(accountNumber, "LOGIN_FAILED");
        }
    }

    // ── Private helpers ───────────────────────────────────────────────────────

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

    /**
     * Prompt captcha once. Returns the valid input, or null if invalid.
     */
    private String promptCaptcha(String generatedCaptcha) {
        System.out.print(service.getMessage("login.enterCaptcha") + " ");
        String input = scanner.nextLine().trim();
        String message = service.checkCaptcha(input, generatedCaptcha);
        if (message.equals(service.getMessage("captcha.valid"))) return input;
        System.out.println(message);
        return null;
    }
}
