package business;

/**
 * BUSINESS LOGIC LAYER
 * Locate: identifies the language properties file to load,
 * as required by the assignment guideline (void setLocate(Locate locate)).
 */
public enum Locate {
    EN("En"),
    VI("Vi");

    /** Name of the properties file (En.properties / Vi.properties). */
    private final String fileName;

    Locate(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
