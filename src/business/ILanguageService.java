package business;

/**
 * BUSINESS LOGIC LAYER — abstraction for language switching / localization.
 * Allows high-level classes (Ebank, ValidatorService) to depend on this
 * interface instead of the concrete LanguageService (DIP).
 */
public interface ILanguageService {

    /** Switch UI language to the given locate. */
    void setLocate(Locate locate);

    /** Get a localised message by key. */
    String getMessage(String key);

    /** Currently active locale. */
    Locate getCurrentLocale();
}
