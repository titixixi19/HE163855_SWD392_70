package business;

import data.IMessageStorage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LanguageService implements ILanguageService {

    private final IMessageStorage storage;

    private Map<String, String> messages;
    private Locate currentLocale;

    public LanguageService(IMessageStorage storage) {
        this.storage = storage;
        loadBundle(Locate.EN);
    }

    @Override
    public void setLocate(Locate locate) {
        loadBundle(locate);
    }

    @Override
    public String getMessage(String key) {
        String value = messages.get(key);
        return value != null ? value : "[" + key + "]";
    }

    @Override
    public Locate getCurrentLocale() {
        return currentLocale;
    }

    private void loadBundle(Locate locate) {
        this.currentLocale = locate;
        try {
            this.messages = storage.loadMessages(locate.getFileName());
        } catch (IOException e) {
            this.messages = new HashMap<>();
            System.err.println("Warning: could not load messages for " + locate.getFileName());
        }
    }
}
