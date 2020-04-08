package concatenatedwordschallenge.model;

public class WordInfo {

    private String firstLongestWord;

    private String secondLongestWord;

    private int size;

    public WordInfo() {
    }

    public String getFirstLongestWord() {
        return firstLongestWord;
    }

    public void setFirstLongestWord(String firstLongestWord) {
        this.firstLongestWord = firstLongestWord;
    }

    public String getSecondLongestWord() {
        return secondLongestWord;
    }

    public void setSecondLongestWord(String secondLongestWord) {
        this.secondLongestWord = secondLongestWord;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "First longest concatenated word: " + firstLongestWord + '\n'
                + "Second longest concatenated word: " + secondLongestWord + '\n'
                + "Total amount of concatenated word in file: " + size;
    }
}
