package business;

import java.util.Random;

/**
 * BUSINESS LOGIC LAYER
 * Generates random captcha codes. Single responsibility: captcha creation.
 * (Function 4 of the assignment: String generateCaptcha().)
 */
public class CaptchaService implements ICaptchaService {

    private static final String CAPTCHA_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz23456789";
    private static final int    CAPTCHA_LENGTH = 6;

    private final Random random;

    public CaptchaService() {
        this(new Random());
    }

    /** Injectable Random for deterministic testing. */
    public CaptchaService(Random random) {
        this.random = random;
    }

    @Override
    public String generateCaptcha() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            char c = CAPTCHA_CHARS.charAt(random.nextInt(CAPTCHA_CHARS.length()));
            sb.append(c);
        }
        return sb.toString();
    }
}
