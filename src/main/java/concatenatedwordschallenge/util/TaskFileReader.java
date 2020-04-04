package concatenatedwordschallenge.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.log4j.Logger;

public class TaskFileReader {

    private static final Logger LOGGER = Logger.getLogger(TaskFileReader.class);

    public TaskFileReader() {
    }

    public List<String> getWordsFromFile(String fileName) {
        String path = getPathToResourcesFile(fileName);
        String wordsFromFile = "";
        try {
            wordsFromFile = Files.readString(Paths.get(path));
        } catch (IOException e) {
            LOGGER.error("Could not read file by path: " + path, e);
        }
        return List.of(wordsFromFile.split(" "));
    }

    /**
     * For some reason, Files.read() method can not directly access file from resources folder
     * so, had to implement this method for obtaining direct path to task file
     */
    private String getPathToResourcesFile(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }
}
