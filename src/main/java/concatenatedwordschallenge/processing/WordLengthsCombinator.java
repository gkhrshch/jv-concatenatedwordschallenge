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

    private Map<Integer, List<Integer>> getGreaterLengthsWithLesserLengths(String fileName) {
        Map<Integer, List<Integer>> greaterLengthsWithLesserLengths = new HashMap<>();
        Set<Integer> possibleLengths = initialWordsProcessor.getWordsWithLengths(fileName).keySet();
        List<Integer> possibleLengthsList = new ArrayList<>(possibleLengths);
        for (int numberWithGreaterLengthIndex = possibleLengthsList.size() - 1;
                numberWithGreaterLengthIndex > 0; numberWithGreaterLengthIndex--) {
            int numberWithGreaterLength = possibleLengthsList.get(numberWithGreaterLengthIndex);
            greaterLengthsWithLesserLengths.put(numberWithGreaterLength, new ArrayList<>());
            for (int numberWithLesserLengthIndex = 0;
                    numberWithLesserLengthIndex < numberWithGreaterLengthIndex;
                    numberWithLesserLengthIndex++) {
                greaterLengthsWithLesserLengths.get(numberWithGreaterLength)
                        .add(possibleLengthsList.get(numberWithLesserLengthIndex));
            }
        }
        return greaterLengthsWithLesserLengths;
    }

    public List<List<Integer>> getUniqueLengthCombinations(String fileName) {
        List<List<Integer>> lengthCombinations = new ArrayList<>();
        Map<Integer, List<Integer>> greaterLengthsWithLesserLengths
                    = getGreaterLengthsWithLesserLengths(fileName);
        for (Map.Entry<Integer, List<Integer>> entry : greaterLengthsWithLesserLengths.entrySet()) {
            List<List<Integer>> combinationsFromEntry = new ArrayList<>();
            writeSumCombinations(entry.getValue(), entry.getKey(),
                    combinationsFromEntry, 0, new ArrayList<>());
            lengthCombinations.addAll(combinationsFromEntry);
        }
        return lengthCombinations;
    }

    private void writeSumCombinations(List<Integer> numbersToCheck, int sum,
                                      List<List<Integer>> result,
                                      int currentSum, List<Integer> currentCombination) {
        if (currentSum > sum) {
            return;
        }
        if (currentSum == sum) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }
        for (int i = 0; i < numbersToCheck.size(); i++) {
            currentCombination.add(numbersToCheck.get(i));
            writeSumCombinations(numbersToCheck, sum, result,
                    currentSum + numbersToCheck.get(i), currentCombination);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    public List<List<Integer>> getLengthCombinationsWithPermutations(String fileName) {
        List<List<Integer>> uniqueLengthCombinations = getUniqueLengthCombinations(fileName);
        List<List<Integer>> lengthCombinationsWithPermutations = new ArrayList<>();
        for (List<Integer> combination : uniqueLengthCombinations) {
            List<List<Integer>> permutations = new ArrayList<>();
            writeAllPermutations(combination.size(), combination, permutations);
            lengthCombinationsWithPermutations.addAll(permutations);
        }
        return lengthCombinationsWithPermutations;
    }

    private void writeAllPermutations(int n, List<Integer> numbers, List<List<Integer>> result) {
        if (n == 1) {
            result.add(new ArrayList<>(numbers));
        } else {
            for (int i = 0; i < n - 1; i++) {
                writeAllPermutations(n - 1, numbers, result);
                if (n % 2 == 0) {
                    swap(numbers, i, n - 1);
                } else {
                    swap(numbers, 0, n - 1);
                }
            }
            writeAllPermutations(n - 1, numbers, result);
        }
    }

    private void swap(List<Integer> input, int a, int b) {
        int toSwap = input.get(a);
        input.set(a, input.get(b));
        input.set(b, toSwap);
    }
}
