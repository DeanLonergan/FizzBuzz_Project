package controllers;

import models.Word;
import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * ProgramAPI class.
 *
 * @author Dean Lonergan
 * @version 0.0.4
 */
public class ProgramAPI {

    private int fizzBuzzLength = 100;
    private List<Word> words;

    public ProgramAPI() {
        this.words = new ArrayList<>();
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public int getFizzBuzzLength() {
        return fizzBuzzLength;
    }

    public void setFizzBuzzLength(int size) {
        if (Utilities.validSize(size)) {
            fizzBuzzLength = size;
        } else {
            fizzBuzzLength = 100;
        }
    }

    public String fizzBuzz() {
        //Initialize a StringBuilder named output.
        StringBuilder output = new StringBuilder();
        //Initialize a boolean named addNumber and set it to false.
        boolean addNumber = false;
        //Loop as many times as the user has specified (arraySize has been set by the user).
        for (int i = 1; i < fizzBuzzLength + 1; i++) {
            //Append a line break.
            output.append("\n");
            //Loop through each word.
            for (int word = 0; word < words.size(); word++) {
                //If the current number of i is a multiple that should be replaced.
                if (i % words.get(word).getNumber() == 0) {
                    //Add the word in the place of the number.
                    output.append(words.get(word).getWord()).append(" ");
                    //Set addNumber to false.
                    addNumber = false;
                    //If more than one word should replace a number.
                    if (words.size() > 1) {
                        //Loop through each of the remaining words.
                        for (int otherWord = 1; otherWord < words.size(); otherWord++) {
                            //If they are also multiples of the number (and not duplicates).
                            if ((i % words.get(otherWord).getNumber() == 0) && (words.get(word).getNumber() != words.get(otherWord).getNumber())) {
                                //Add them after the first word.
                                output.append(words.get(otherWord).getWord()).append(" ");
                            }
                        }
                    }
                    break;
                //Otherwise set add number to true.
                } else {
                    addNumber = true;
                }
            }
            //When add number is true.
            if (addNumber) {
                //Add the number.
                output.append(i);
            }
        }
        return output.toString();
    }
}