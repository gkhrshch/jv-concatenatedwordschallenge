import concatenatedwordschallenge.processing.InitialWordsProcessor;
import concatenatedwordschallenge.util.TaskFileReader;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static final Logger     LOGGER     = Logger.getLogger(Main.class);

    private static String           fileName;

    static {
        LOGGER.warn("Input file name, please");
        try (Scanner scanner = new Scanner(System.in)) {
            fileName = scanner.nextLine();
        }
    }

    public static void main(String[] args) {

        TaskFileReader taskFileReader = new TaskFileReader(fileName);
        InitialWordsProcessor initialWordsProcessor = new InitialWordsProcessor(taskFileReader);
        LOGGER.debug(initialWordsProcessor.getWordsWithLengths());
    }
}
