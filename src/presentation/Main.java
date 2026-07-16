package presentation;

import business.CaptchaService;
import business.Ebank;
import business.ICaptchaService;
import business.IEbankService;
import business.ILanguageService;
import business.IValidatorService;
import business.LanguageService;
import business.Locate;
import business.ValidatorService;
import data.AccountDAO;
import data.FileHelper;
import data.IAccountDAO;
import data.IAccountStorage;

import java.util.Scanner;

/**
 * PRESENTATION LAYER — Entry point and Composition Root.
 * This is the ONLY place allowed to `new` concrete classes: it builds the
 * object graph and injects every dependency through constructors (DIP).
 * Displays the language selection menu, then delegates to LoginUI.
 * Follows 3-layer: Presentation → Business → Data.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ── Composition root: build the graph, wire dependencies ────────────────
        ILanguageService  langService = new LanguageService();                 // BLL
        IValidatorService validator   = new ValidatorService(langService);     // BLL
        ICaptchaService   captcha     = new CaptchaService();                   // BLL
        IAccountStorage   storage     = new FileHelper();                       // DAL
        IAccountDAO       accountDAO  = new AccountDAO(storage);                // DAL
        IEbankService     ebank       = new Ebank(langService, validator,       // BLL Facade
                                                  captcha, accountDAO);
        LoginUI           loginUI     = new LoginUI(ebank, scanner);            // PL

        boolean running = true;

        while (running) {
            // Menu starts in English (default locale); after the user picks a
            // language it is displayed in that language (spec: option 1 switches
            // to Vietnamese, option 2 keeps English).
            printMenu(ebank);
            System.out.print(ebank.getMessage("menu.select"));
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    ebank.setLocate(Locate.VI);
                    loginUI.startLogin();
                    break;
                case "2":
                    ebank.setLocate(Locate.EN);
                    loginUI.startLogin();
                    break;
                case "3":
                    System.out.println(ebank.getMessage("menu.exit"));
                    running = false;
                    break;
                default:
                    System.out.println(ebank.getMessage("menu.invalid"));
            }
        }
        scanner.close();
    }

    private static void printMenu(IEbankService ebank) {
        System.out.println();
        System.out.println(ebank.getMessage("menu.title"));
        System.out.println(ebank.getMessage("menu.option1"));
        System.out.println(ebank.getMessage("menu.option2"));
        System.out.println(ebank.getMessage("menu.option3"));
    }
}
