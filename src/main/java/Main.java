import concatenatedwordschallenge.processing.InitialWordsProcessor;
import concatenatedwordschallenge.processing.WordLengthsCombinator;
import concatenatedwordschallenge.util.TaskFileReader;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    private static String fileName;

    static {
        LOGGER.info("Input file name, please");
        try (Scanner scanner = new Scanner(System.in)) {
            fileName = scanner.nextLine();
        }
    }

    public static void main(String[] args) {

        TaskFileReader taskFileReader = new TaskFileReader();
        InitialWordsProcessor initialWordsProcessor = new InitialWordsProcessor(taskFileReader);
        WordLengthsCombinator wordLengthsCombinator
                = new WordLengthsCombinator(initialWordsProcessor);
        long timeBeforeCalculation = System.currentTimeMillis();
        LOGGER.debug(wordLengthsCombinator.getLengthCombinationsWithPermutations(fileName));
        LOGGER.debug(System.currentTimeMillis() - timeBeforeCalculation);
    }
}
