package concatenatedwordschallenge.processing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordLengthsCombinator {

    private final InitialWordsProcessor initialWordsProcessor;

    public WordLengthsCombinator(InitialWordsProcessor initialWordsProcessor) {
        this.initialWordsProcessor = initialWordsProcessor;
    }

    private Map<Integer, List<Integer>> getGreaterLengthsWithLesserLengths() {
        Map<Integer, List<Integer>> greaterLengthsWithLesserLengths = new HashMap<>();
        Set<Integer> possibleLengths = initialWordsProcessor.getWordsWithLengths().keySet();
        List<Integer> possibleLengthsList = new ArrayList<>(possibleLengths);
        for (int numberWithGreaterLengthIndex = possibleLengthsList.size() - 1; numberWithGreaterLengthIndex > 0; numberWithGreaterLengthIndex--) {
            int numberWithGreaterLength = possibleLengthsList.get(numberWithGreaterLengthIndex);
            greaterLengthsWithLesserLengths.put(numberWithGreaterLength, new ArrayList<>());
            for (int numberWithLesserLengthIndex = 0; numberWithLesserLengthIndex < numberWithGreaterLengthIndex; numberWithLesserLengthIndex++ ) {
                greaterLengthsWithLesserLengths.get(numberWithGreaterLength).add(possibleLengthsList.get(numberWithLesserLengthIndex));
            }
        }
        return greaterLengthsWithLesserLengths;
    }

    public List<List<Integer>> getLengthCombinations() {
        List<List<Integer>> lengthCombinations = new ArrayList<>();
        Map<Integer, List<Integer>> greaterLengthsWithLesserLengths = getGreaterLengthsWithLesserLengths();
        for (Map.Entry<Integer, List<Integer>> entry : greaterLengthsWithLesserLengths.entrySet()) {
            List<List<Integer>> combinationsFromEntry = new ArrayList<>();
            writeSumCombinations(entry.getValue(), entry.getKey(), new ArrayList<>(), combinationsFromEntry);
            lengthCombinations.addAll(combinationsFromEntry);
        }
        return lengthCombinations;
    }

    /**
     *
     * Does not return variants with duplicating numbers atm, e.g [1, 1, 1] for 3 etc.
     */
    private void writeSumCombinations(List<Integer> numbersToCheck, int sum, List<Integer> partial, List<List<Integer>> result) {
        int s = 0;
        for (int x: partial) {
            s += x;
        }
        if (s == sum) {
            result.add(partial);
        }
        if (s >= sum) {
            return;
        }
        for (int i=0; i<numbersToCheck.size(); i++) {
            ArrayList<Integer> remaining = new ArrayList<>();
            int n = numbersToCheck.get(i);
            for (int j=i+1; j<numbersToCheck.size();j++) remaining.add(numbersToCheck.get(j));
            ArrayList<Integer> partialRecursive = new ArrayList<>(partial);
            partialRecursive.add(n);
            writeSumCombinations(remaining, sum, partialRecursive, result);
        }
    }
}
