package concatenatedwordschallenge.processing;

import concatenatedwordschallenge.util.TaskFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitialWordsProcessor {

    private final TaskFileReader taskFileReader;

    public InitialWordsProcessor(TaskFileReader taskFileReader) {
        this.taskFileReader = taskFileReader;
    }

    public Map<Integer, List<String>> getWordsWithLengths() {
        Map<Integer, List<String>> wordsByLengths = new HashMap<>();
        List<String> words = taskFileReader.getWordsFromFile();
        int longestWordLength = words.get(words.size() - 1).length();
        for (int i = 1; i <= longestWordLength; i++) {
            wordsByLengths.put(i, new ArrayList<>());
        }
        for (String word : words) {
            wordsByLengths.get(word.length()).add(word);
        }
        return wordsByLengths;
    }
}
