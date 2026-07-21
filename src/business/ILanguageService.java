package business;

public interface ILanguageService {
    void setLocate(Locate locate);
    String getMessage(String key);
    Locate getCurrentLocale();
}
