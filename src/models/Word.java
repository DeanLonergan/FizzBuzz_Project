package models;

import utils.Utilities;

/**
 * Word class.
 *
 * @author Dean Lonergan
 * @version 0.0.4
 */
public class Word {

    private String word;
    private int number;

    /**
     * The constructor allowing word objects to be created.
     * @param word the word entered by the user.
     * @param number the multiple that the word will replace.
     */
    public Word(String word, int number) {
        if (Utilities.max15Chars(word)) {
            this.word = word;
        } else {
            this.word = word.substring(0, 15);
        }
        if (Utilities.validPositive(number)) {
            this.number = number;
        } else {
            this.number = -1;
        }
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
