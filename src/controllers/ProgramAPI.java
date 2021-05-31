package controllers;

import models.Word;
import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * ProgramAPI class.
 *
 * @author Dean Lonergan
 * @version 0.0.6
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

    public String listWords() {
        if (words.isEmpty()){
            return "\nNo words have been added yet.";
        }
        else{
            StringBuilder listOfWords = new StringBuilder("\n");
            for (int i = 0; i < words.size(); i++){
                listOfWords.append(i).append(": ").append(words.get(i).getWord()).append(" [Replaces multiples of: ").append(words.get(i).getNumber()).append("]\n");
            }
            return listOfWords.toString();
        }
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
        StringBuilder output = new StringBuilder();
        boolean addNumber = true;
        for (int i = 1; i < fizzBuzzLength + 1; i++) {   //Loop as many times as the user has specified (arraySize has been set by the user).
            output.append("\n");   //Append a line break.
            for (int word = 0; word < words.size(); word++) {   //Loop through each word.
                if (i % words.get(word).getNumber() == 0) {   //If the current number of i is a multiple that should be replaced.
                    output.append(words.get(word).getWord()).append(" ");   //Add the word in the place of the number.
                    addNumber = false;   //Set addNumber to false.
                    if (words.size() > 1) {   //If more than one word should replace a number.
                        for (int otherWord = 1; otherWord < words.size(); otherWord++) {   //Loop through each of the remaining words.
                            if ((i % words.get(otherWord).getNumber() == 0) && (words.get(word).getNumber() != words.get(otherWord).getNumber())) { //If they are also multiples of the number (and not duplicates).
                                output.append(words.get(otherWord).getWord()).append(" ");   //Add them after the first word.
                            }
                        }
                    }
                    break;
                } else {   //Otherwise set add number to true.
                    addNumber = true;
                }
            }
            if (addNumber) {   //When add number is true.
                output.append(i);   //Add the number.
            }
        }
        return output.toString();
    }
}