package presentation;

import business.EbankFactory;
import business.IEbankService;
import business.Locate;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        IEbankService ebank   = EbankFactory.create();                          // BLL
        LoginUI       loginUI = new LoginUI(ebank, scanner);                     // PL

        boolean running = true;

        while (running) {

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
