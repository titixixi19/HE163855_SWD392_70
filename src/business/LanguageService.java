package business;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * BUSINESS LOGIC LAYER
 * Manages language switching and message retrieval.
 * Reads from En.properties or Vi.properties (UTF-8).
 */
public class LanguageService implements ILanguageService {

    private Properties bundle;
    private Locate currentLocale;

    public LanguageService() {
        loadBundle(Locate.EN); // default English
    }

    /**
     * Switch language to the given locate.
     */
    @Override
    public void setLocate(Locate locate) {
        loadBundle(locate);
    }

    /**
     * Get a localised message by key.
     * Returns the key itself if not found (safe fallback).
     */
    @Override
    public String getMessage(String key) {
        return bundle.getProperty(key, "[" + key + "]");
    }

    @Override
    public Locate getCurrentLocale() {
        return currentLocale;
    }

    private void loadBundle(Locate locate) {
        this.currentLocale = locate;
        this.bundle = new Properties();
        String path = "resources/" + locate.getFileName() + ".properties";
        try (InputStreamReader reader =
                     new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)) {
            bundle.load(reader);
        } catch (IOException e) {
            System.err.println("Warning: could not load " + path + ". Using empty bundle.");
        }
    }
}
