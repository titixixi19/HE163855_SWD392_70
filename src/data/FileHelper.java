package data;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileHelper implements IAccountStorage {

    private static final String ACCOUNTS_FILE = "resources/accounts.csv";
    private static final String LOG_FILE      = "resources/log.txt";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
    @Override
    public List<Account> loadAccounts() throws IOException {
        List<Account> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; }
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length == 3) {
                    list.add(new Account(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                }
            }
        }
        return list;
    }

    
    @Override
    public Account findByAccountNumber(String accountNumber) throws IOException {
        for (Account acc : loadAccounts()) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    
    @Override
    public void writeLog(String accountNumber, String event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now().format(FORMATTER);
            writer.write(timestamp + " | " + accountNumber + " | " + event);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Warning: could not write to log file.");
        }
    }
}
