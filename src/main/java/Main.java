import concatenatedwordschallenge.processing.InitialWordsProcessor;
import concatenatedwordschallenge.util.TaskFileReader;

import org.apache.log4j.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    private static final String FILE_NAME = "words.txt";

    public static void main(String[] args) {

        TaskFileReader taskFileReader = new TaskFileReader(FILE_NAME);
        InitialWordsProcessor initialWordsProcessor = new InitialWordsProcessor(taskFileReader);
        logger.debug(initialWordsProcessor.getWordsWithLengths());
    }
}
