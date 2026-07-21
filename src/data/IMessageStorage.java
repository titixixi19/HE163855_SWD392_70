package data;

import java.io.IOException;
import java.util.Map;

public interface IMessageStorage {

    Map<String, String> loadMessages(String bundleName) throws IOException;
}
