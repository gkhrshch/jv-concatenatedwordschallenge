import concatenatedwordschallenge.model.WordInfo;
import concatenatedwordschallenge.processing.WordService;
import concatenatedwordschallenge.util.TaskFileReader;

import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        String fileName;
        LOGGER.info("Input file name, please");
        try (Scanner scanner = new Scanner(System.in)) {
            fileName = scanner.nextLine();
        }

        List<String> words = new TaskFileReader().getWordsFromFile(fileName);
        WordService wordService = new WordService();
        WordInfo wordInfo = wordService.getWordInfo();
        LOGGER.info(wordInfo.toString());
    }
}
