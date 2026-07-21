package business;


public enum Locate {
    EN("En"),
    VI("Vi");

    private final String fileName;

    Locate(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
