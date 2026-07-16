package business;

/**
 * BUSINESS LOGIC LAYER — abstraction for captcha generation.
 * Extracted from Ebank so captcha logic has a single responsibility (SRP)
 * and Ebank can depend on this abstraction (DIP).
 */
public interface ICaptchaService {

    /** Generate a random captcha string. */
    String generateCaptcha();
}
