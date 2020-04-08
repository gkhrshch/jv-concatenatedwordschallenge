package concatenatedwordschallenge.processing;

import concatenatedwordschallenge.model.WordInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordService {

    private WordInfo wordInfo = new WordInfo();

    public WordService() {
    }

    public WordInfo getWordInfo() {
        //TODO: concatenated words identification logic
        return wordInfo;
    }

    public Map<Integer, List<String>> getWordsWithLengths(List<String> words) {
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

    private Map<Integer, List<Integer>> getGreaterLengthsWithLesserLengths(List<String> words) {
        Map<Integer, List<Integer>> greaterLengthsWithLesserLengths = new HashMap<>();
        Set<Integer> possibleLengths = getWordsWithLengths(words).keySet();
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

    public List<List<Integer>> getUniqueLengthCombinations(List<String> words) {
        List<List<Integer>> lengthCombinations = new ArrayList<>();
        Map<Integer, List<Integer>> greaterLengthsWithLesserLengths
                    = getGreaterLengthsWithLesserLengths(words);
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

    public List<List<Integer>> getLengthCombinationsWithPermutations(List<String> words) {
        List<List<Integer>> uniqueLengthCombinations = getUniqueLengthCombinations(words);
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


//    public List<String> getPotentiallyCompositeWords(List<String> words) {
//        System.out.println(words.size());
//        Set<String> potentiallyCompositeWordsSet = new HashSet<>();
//        for (String containingWord : words) {
//            for (String containedWord : words) {
//                if (containedWord.length() >= containingWord.length()) {
//                    break;
//                }
//                if (containingWord.substring(0, containedWord.length()).equals(containedWord)) {
//                    potentiallyCompositeWordsSet.add(containingWord);
//                }
//            }
//        }
//        System.out.println(potentiallyCompositeWordsSet.size());
//        return potentiallyCompositeWordsSet.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
//    }
//
//    public Map<String, List<String>> getPotentiallyCompositeWordsWithFirstContainedWords(List<String> words) {
//        System.out.println(words.size());
//        Map<String, List<String>> potentiallyCompositeWordsWithFirstContainedWords = new HashMap<>();
//        for (String containingWord : words) {
//            potentiallyCompositeWordsWithFirstContainedWords.put(containingWord, new ArrayList<>());
//            for (String containedWord : words) {
//                if (containedWord.length() >= containingWord.length()) {
//                    break;
//                }
//                if (containingWord.substring(0, containedWord.length()).equals(containedWord)) {
//                    potentiallyCompositeWordsWithFirstContainedWords.get(containingWord).add(containedWord);
//                }
//            }
//            if (potentiallyCompositeWordsWithFirstContainedWords.get(containingWord).isEmpty()) {
//                potentiallyCompositeWordsWithFirstContainedWords.remove(containingWord);
//            }
//        }
//        System.out.println(potentiallyCompositeWordsWithFirstContainedWords.keySet().size());
//        return potentiallyCompositeWordsWithFirstContainedWords;
//    }
//
//    public Map<String, Map<Integer, List<String>>> getPotentiallyCompositeWordsWithFirstContainedWordsNew(List<String> words) {
//        System.out.println(words.size());
//        Map<String, Map<Integer, List<String>>> potentiallyCompositeWordsWithFirstContainedWords = new HashMap<>();
//        for (String containingWord : words) {
//            createEmptyEntry(containingWord, potentiallyCompositeWordsWithFirstContainedWords);
//            for (String containedWord : words) {
//                if (containedWord.length() >= containingWord.length()) {
//                    break;
//                }
//                if (containingWord.substring(0, containedWord.length()).equals(containedWord)) {
//                    potentiallyCompositeWordsWithFirstContainedWords.get(containingWord).get(0).add(containedWord);
//                }
//            }
//            if (potentiallyCompositeWordsWithFirstContainedWords.get(containingWord).get(0).isEmpty()) {
//                potentiallyCompositeWordsWithFirstContainedWords.remove(containingWord);
//            }
//        }
//        System.out.println(potentiallyCompositeWordsWithFirstContainedWords.keySet().size());
//        return potentiallyCompositeWordsWithFirstContainedWords;
//    }
//
//    private void createEmptyEntry(String containingWord,
//                                  Map<String, Map<Integer, List<String>>>
//                                          potentiallyCompositeWordsWithFirstContainedWords) {
//        Map<Integer, List<String>> firstContainedWords = new HashMap<>();
//        firstContainedWords.put(0, new ArrayList<>());
//        potentiallyCompositeWordsWithFirstContainedWords.put(containingWord, firstContainedWords);
//    }
}
