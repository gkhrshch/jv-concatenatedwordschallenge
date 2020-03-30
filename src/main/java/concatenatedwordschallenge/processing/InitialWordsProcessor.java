package concatenatedwordschallenge.processing;

import concatenatedwordschallenge.util.TaskFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitialWordsProcessor {

    private List<String> words;

    private Map<Integer, List<String>> wordsWithLengths;

    public InitialWordsProcessor(TaskFileReader taskFileReader) {
        this.words = new ArrayList<>(taskFileReader.getWords());
        this.wordsWithLengths = groupWordsByLengths();
    }

    private Map<Integer, List<String>> groupWordsByLengths() {
        Map<Integer, List<String>> wordsByLengths = new HashMap<>();
        int longestWordLength = words.get(words.size() - 1).length();
        for (int i = 1; i <= longestWordLength; i++) {
            wordsByLengths.put(i, new ArrayList<>());
        }
        for (String word : words) {
            wordsByLengths.get(word.length()).add(word);
        }
        return wordsByLengths;
    }

    public Map<Integer, List<String>> getWordsWithLengths() {
        return wordsWithLengths;
    }
}
