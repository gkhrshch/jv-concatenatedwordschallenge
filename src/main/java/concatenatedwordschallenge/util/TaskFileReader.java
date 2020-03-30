package concatenatedwordschallenge.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class TaskFileReader {

    private static Logger logger = Logger.getLogger(TaskFileReader.class);

    private List<String> words;

    public TaskFileReader (String fileName) {
        this.words = getWordsFromFile(getPathToResourcesFile(fileName));
    }

    private List<String> getWordsFromFile(String path) {
        String wordsFromFile = "";
        try {
            wordsFromFile = Files.readString(Paths.get(path));
        } catch (IOException e) {
            logger.error(e);
        }
        return List.of(wordsFromFile.split(" "));
    }


    /**
     * For some reason, Files.read() method can not directly access file from resources folder
     * so, had to implement this method for obtaining direct path to task file
     */
    private String getPathToResourcesFile(String filename) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        return file.getAbsolutePath();
    }

    public List<String> getWords() {
        return words;
    }
}
