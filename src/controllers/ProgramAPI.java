package controllers;

import models.Word;
import utils.ScannerInput;
import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * ProgramAPI class.
 *
 * @author Dean Lonergan
 * @version 0.0.8
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
            return "No words have been added yet.";
        }
        else{
            StringBuilder listOfWords = new StringBuilder("\n");
            for (int i = 0; i < words.size(); i++){
                listOfWords.append(i + 1).append(": ").append(words.get(i).getWord()).append(" [Replaces multiples of: ").append(words.get(i).getNumber()).append("]\n");
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
        for (int i = 1; i < fizzBuzzLength + 1; i++) {                                              //Loop as many times as the user has specified.
            output.append("\n");
            for (int word = 0; word < words.size(); word++) {                                       //Loop through each word.
                if (i % words.get(word).getNumber() == 0) {                                         //If the current number of i is a multiple that should be replaced.
                    output.append(words.get(word).getWord()).append(" ");                           //Add the word in place of the number.
                    addNumber = false;                                                              //Set addNumber to false.
                    if (words.size() > 1) {                                                         //If more than one word should replace a number.
                        for (int otherWord = 1; otherWord < words.size(); otherWord++) {            //Loop through each of the remaining words.
                            if ((i % words.get(otherWord).getNumber() == 0) && (words.get(word).getNumber() != words.get(otherWord).getNumber())) {   //If another word is also a multiple of the number (and not a duplicate).
                                output.append(words.get(otherWord).getWord()).append(" ");          //Append it.
                            }
                        }
                    }
                    break;
                } else {                                                                            //Otherwise set add number to true.
                    addNumber = true;
                }
            }
            if (addNumber) {                                                                        //When add number is true.
                output.append(i);                                                                   //Append the number.
            }
        }
        return output.toString();
    }

    public void classicFizzBuzz() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("------------------ Classic FizzBuzz -------------------");
        System.out.println("-------------------------------------------------------");
        ArrayList<Word> previousWords = new ArrayList<>(getWords());
        int previousLength = getFizzBuzzLength();
        setFizzBuzzLength(100);
        getWords().clear();
        addWord(new Word("Fizz", 3));
        addWord(new Word("Buzz", 5));
        System.out.println(fizzBuzz());
        setWords(previousWords);
        setFizzBuzzLength(previousLength);
    }

    public int customMenu() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("------------------- Custom FizzBuzz -------------------");
        System.out.println("-------------------------------------------------------");
        System.out.println(" (1) Run your FizzBuzz");
        System.out.println(" (2) Words [Current: " + getWords().size() + " word(s)]");
        System.out.println(" (3) Length [Current: " + getFizzBuzzLength() + "]");
        System.out.println("-------------------------------------------------------");
        System.out.println(" (8) Save");
        System.out.println(" (9) Load");
        System.out.println(" (0) Main menu");
        System.out.println("-------------------------------------------------------");
        return ScannerInput.validNextInt("INPUT ==>>: ");
    }

    public int wordMenu() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("------------------------ Words ------------------------");
        System.out.println("-------------------------------------------------------");
        System.out.println(listWords());
        System.out.println("-------------------------------------------------------");
        System.out.println(" (1) Add word");
        System.out.println(" (2) Update word");
        System.out.println(" (3) Remove word");
        System.out.println("-------------------------------------------------------");
        System.out.println(" (0) Menu");
        System.out.println("-------------------------------------------------------");
        return ScannerInput.validNextInt("INPUT ==>>: ");
    }

    public void addWord() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("----------------------- Add Word ----------------------");
        System.out.println("-------------------------------------------------------");
        String stringInput = "";
        while (stringInput.isEmpty()) {
            stringInput = ScannerInput.validNextLine("\nEnter the word would you like to add: ");
        }
        int intInput = ScannerInput.validNextInt("Enter the multiple you would like " + "\"" +  stringInput + "\"" + " to replace: ");
        if (intInput == 1) {
            char userConfirmOne = ScannerInput.validNextChar("\nReplacing multiples of 1 will replace all numbers, are you sure? (Y/N): ");
            if (!Utilities.validYesInput(userConfirmOne)) {
                while (intInput == 1) intInput = ScannerInput.validNextInt("\nPlease enter a different multiple to replace: ");
            }
        }
        for (Word word : getWords()) {
            if (word.getWord().equalsIgnoreCase(stringInput)) {
                char userConfirmDuplicate = ScannerInput.validNextChar("\nThe word " + "\"" +  stringInput + "\"" + " is already in the FizzBuzz, would you like to add it anyway? (Y/N): ");
                if (!Utilities.validYesInput(userConfirmDuplicate)) {
                    return;
                }
            }
            if (word.getNumber() == intInput) {
                int previousInput = intInput;
                while (previousInput == intInput) {
                    intInput = ScannerInput.validNextInt("\nThe number " + intInput + " has already been selected, please enter a different multiple to replace: ");
                }
            }
        }
        char userConfirmReplace = ScannerInput.validNextChar("\nWould you like the word " + "\"" +  stringInput + "\"" + " to replace all multiples of " + intInput +"? (Y/N): ");
        if (Utilities.validYesInput(userConfirmReplace)) {
            addWord(new Word(stringInput, intInput));
            System.out.println("\n" + "\"" + stringInput + "\"" + " will now replace all multiples of " + intInput + " in your FizzBuzz.");
        }
    }

    public void updateWord() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("--------------------- Update Word ---------------------");
        System.out.println("-------------------------------------------------------");
        System.out.println(listWords());
        if (!getWords().isEmpty()) {
            int index = ScannerInput.validNextInt("Enter the index of the word you would like to update: ") - 1;
            if (Utilities.validIndex(index, getWords())) {
                System.out.println("\n-------------------------------------------------------");
                System.out.println("--------------------- Update Word ---------------------");
                System.out.println("-------------------------------------------------------");
                System.out.println(" (1) Update the word: " + "\"" + getWords().get(index).getWord() + "\"");
                System.out.println(" (2) Update the multiple: " + getWords().get(index).getNumber());
                System.out.println("-------------------------------------------------------");
                System.out.println(" (0) Return ");
                System.out.println("-------------------------------------------------------");
                int option = ScannerInput.validNextInt("INPUT ==>>: ");
                while (option != 0) {
                    switch (option) {
                        case 1 -> {
                            String stringInput = ScannerInput.validNextLine("\nEnter the updated word: ");
                            for (Word word : getWords()) {
                                if (word.getWord().equalsIgnoreCase(stringInput)) {
                                    char userConfirmDuplicate = ScannerInput.validNextChar("\nThe word " + "\"" +  stringInput + "\"" + " is already in the FizzBuzz, would you like to add it anyway? (Y/N): ");
                                    if (!Utilities.validYesInput(userConfirmDuplicate)) {
                                        return;
                                    }
                                }
                            }
                            getWords().get(index).setWord(stringInput);
                            System.out.println("\n" + "\"" + stringInput + "\"" + " will now replace all multiples of " + getWords().get(index).getNumber() + " in your FizzBuzz.");
                            return;
                        }
                        case 2 -> {
                            int intInput = ScannerInput.validNextInt("\nEnter the updated multiple: ");
                            outer: for (Word word : getWords()) {
                                while (intInput == getWords().get(index).getNumber() || intInput == 1 || intInput == word.getNumber()) {
                                    if (intInput == getWords().get(index).getNumber()) {
                                        char userConfirmOne = ScannerInput.validNextChar("\nMultiples of " + intInput + " are already replaced by " + "\"" + getWords().get(index).getWord() + "\"" + ", would you like to change this number? (Y/N): ");
                                        if (!Utilities.validYesInput(userConfirmOne)) {
                                            break outer;
                                        }
                                        intInput = ScannerInput.validNextInt("\nPlease enter a different number: ");
                                    }
                                    if (intInput == 1) {
                                        char userConfirmOne = ScannerInput.validNextChar("\nReplacing multiples of 1 will replace all numbers, are you sure? (Y/N): ");
                                        if (Utilities.validYesInput(userConfirmOne)) {
                                            break outer;
                                        }
                                        intInput = ScannerInput.validNextInt("\nPlease enter a different number: ");
                                    }
                                    if (intInput == word.getNumber()) {
                                        intInput = ScannerInput.validNextInt("\nMultiples of " + intInput + " are already replaced by " + "\"" + word.getWord() + "\"" + ", please enter a different number: ");
                                    }
                                }
                            }
                            getWords().get(index).setNumber(intInput);
                            System.out.println("\n" + "\"" + getWords().get(index).getWord() + "\"" + " will now replace all multiples of " + intInput + " in your FizzBuzz.");
                            return;
                        }
                    }
                    ScannerInput.validNextLine("\nPress any key to continue...");
                    option = customMenu();
                }
            }
        }
    }

    public void removeWord() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("--------------------- Remove Word ---------------------");
        System.out.println("-------------------------------------------------------");
        System.out.println(listWords());
        if (!getWords().isEmpty()) {
            int index = ScannerInput.validNextInt("Enter the index of the word you would like to remove: ") - 1;
            if (Utilities.validIndex(index, getWords())) {
                char userConfirmDuplicate = ScannerInput.validNextChar("\nAre you sure you would like to remove the word " + "\"" + getWords().get(index).getWord() + "\"" + "? (Y/N): ");
                if (Utilities.validYesInput(userConfirmDuplicate)) {
                    System.out.println("\n" + "\"" + getWords().get(index).getWord() + "\"" + " has been removed.");
                    getWords().remove(index);
                }
            }
        }
    }

    public void changeLength() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("-------------------- Change Length --------------------");
        System.out.println("-------------------------------------------------------");
        int inputSize = ScannerInput.validNextInt("\nWhat length would you like your FizzBuzz to be? (1-1000): ");
        while (!Utilities.validSize(inputSize)) {
            inputSize = ScannerInput.validNextInt("\nThe length you entered is outside of the accepted range (1-1000), please enter a valid length: ");
        }
        setFizzBuzzLength(inputSize);
        System.out.println("\nThe length has been set to " + inputSize);
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("custom.xml"));
        fizzBuzzLength = is.readInt();
        words = (ArrayList<Word>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("custom.xml"));
        out.writeInt(fizzBuzzLength);
        out.writeObject(words);
        out.close();
    }
}