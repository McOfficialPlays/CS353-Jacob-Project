package project.process;

public class FileOutputDestination implements OutputDestination {

    private final String path;

    public FileOutputDestination(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}