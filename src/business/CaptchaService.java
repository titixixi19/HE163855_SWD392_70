package business;

import java.util.Random;


public class CaptchaService implements ICaptchaService {

    private static final String CAPTCHA_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz23456789";
    private static final int    CAPTCHA_LENGTH = 6;
    private final Random random;
    public CaptchaService() {
        this(new Random());
    }
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
