package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MessageFileHelper implements IMessageStorage {

    private static final String MESSAGE_DIR = "resources/";
    private static final String EXTENSION   = ".properties";

    @Override
    public Map<String, String> loadMessages(String bundleName) throws IOException {
        Properties props = new Properties();
        String path = MESSAGE_DIR + bundleName + EXTENSION;

        try (InputStreamReader reader =
                     new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)) {
            props.load(reader);
        }

        Map<String, String> messages = new HashMap<>();
        for (String key : props.stringPropertyNames()) {
            messages.put(key, props.getProperty(key));
        }
        return messages;
    }
}
