package project.process;

public class FileInputSource implements InputSource {

    private final String path;

    public FileInputSource(String path) {
        this.path = path;
    }

    @Override
    public String getIdentifier() {
        return path;
    }
}