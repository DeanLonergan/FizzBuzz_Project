package controllers;

import models.Word;
import utils.ScannerInput;
import utils.Utilities;

import java.util.ArrayList;

/**
 * Driver class.
 *
 * @author Dean Lonergan
 * @version 0.0.4
 */
public class Driver {

    private final ProgramAPI pgmAPI;

    public static void main(String[] args) {
        Driver app = new Driver();
        app.runMainMenu();
    }

    public Driver() {
        pgmAPI = new ProgramAPI();
    }

    private int mainMenu() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("------------ Welcome to FizzBuzz: Ultimate ------------");
        System.out.println("-------------------------------------------------------");
        System.out.println(" (1) Classic FizzBuzz");
        System.out.println(" (2) Create your own");
        System.out.println("-------------------------------------------------------");
        System.out.println(" (9) About");
        System.out.println(" (0) Exit");
        System.out.println("-------------------------------------------------------");
        return ScannerInput.validNextInt("INPUT ==>>: ");
    }

    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    try{
                        classicFizzBuzz();
                    }
                    catch(Exception e){
                        System.err.println("Error printing classic FizzBuzz: " + e);
                    }
                    break;
                case 2:
                    try{
                        runCustomMenu();
                    }
                    catch(Exception e){
                        System.err.println("Error entering the custom FizzBuzz menu: " + e);
                    }
                    break;
                case 9:
                    try{
                        printHelp();
                    }
                    catch(Exception e){
                        System.err.println("Error printing help: " + e);
                    }
                    break;
            }
            ScannerInput.validNextLine("\nPress any key to continue...");
            option = mainMenu();
        }
        if (!pgmAPI.getWords().isEmpty() || pgmAPI.getFizzBuzzLength() != 100) {
            char input = ScannerInput.validNextChar("\nWould you like to save your custom FizzBuzz before exiting? (Y/N): ");
            if (Utilities.validYesInput(input)) {
                System.out.println("\nYour custom FizzBuzz has been saved!");
            }
        }
        System.out.println("Exiting... bye");
    }

    public void classicFizzBuzz() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("------------------ Classic FizzBuzz -------------------");
        System.out.println("-------------------------------------------------------");
        ArrayList<Word> previousWords = new ArrayList<>(pgmAPI.getWords());
        int previousLength = pgmAPI.getFizzBuzzLength();
        pgmAPI.setFizzBuzzLength(100);
        pgmAPI.getWords().clear();
        pgmAPI.addWord(new Word("Fizz", 3));
        pgmAPI.addWord(new Word("Buzz", 5));
        System.out.println(pgmAPI.fizzBuzz());
        pgmAPI.setWords(previousWords);
        pgmAPI.setFizzBuzzLength(previousLength);
    }

    private int customMenu() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("------------------- Custom FizzBuzz -------------------");
        System.out.println("-------------------------------------------------------");
        System.out.println(" (1) Run your FizzBuzz");
        System.out.println(" (2) Add word");
        System.out.println(" (3) Update word (not working)");
        System.out.println(" (4) Remove word (not working)");
        System.out.println(" (5) Change length (Current: " + pgmAPI.getFizzBuzzLength() + ")");
        System.out.println("-------------------------------------------------------");
        System.out.println(" (8) Save");
        System.out.println(" (9) Load");
        System.out.println(" (0) Main menu");
        System.out.println("-------------------------------------------------------");
        return ScannerInput.validNextInt("INPUT ==>>: ");
    }

    private void runCustomMenu() {
        int option = customMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    try{
                        System.out.println(pgmAPI.fizzBuzz());
                    }
                    catch(Exception e){
                        System.err.println("Error printing custom FizzBuzz: " + e);
                    }
                    break;
                case 2:
                    try{
                        addWord();
                    }
                    catch(Exception e){
                        System.err.println("Error adding a word: " + e);
                    }
                    break;
                case 5:
                    try{
                        fizzBuzzSize();
                    }
                    catch(Exception e){
                        System.err.println("Error adding a word: " + e);
                    }
                    break;
                case 9:
                    try{
                        printHelp();
                    }
                    catch(Exception e){
                        System.err.println("Error printing help: " + e);
                    }
                    break;
            }
            ScannerInput.validNextLine("\nPress any key to continue...");
            option = customMenu();
        }
    }

    public void addWord() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("------------------- Custom FizzBuzz -------------------");
        System.out.println("-------------------------------------------------------");
        String stringInput = ScannerInput.validNextLine("\nEnter the word would you like to add: ");
        int intInput = ScannerInput.validNextInt("Enter the multiple you would like to replace: ");
        if (intInput == 1) {
            char userConfirmOne = ScannerInput.validNextChar("Replacing multiples of 1 will replace all numbers, are you sure? (Y/N): ");
            if (!Utilities.validYesInput(userConfirmOne)) {
                while (intInput == 1)
                intInput = ScannerInput.validNextInt("\nPlease enter a different multiple to replace: ");
            }
        }
        for (Word word : pgmAPI.getWords()) {
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
            pgmAPI.addWord(new Word(stringInput, intInput));
            System.out.println("\n" + "\"" + stringInput + "\"" + " will now replace all multiples of " + intInput + " in your FizzBuzz.");
        }
    }

    public void fizzBuzzSize() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("------------------- Custom FizzBuzz -------------------");
        System.out.println("-------------------------------------------------------");
        int inputSize = ScannerInput.validNextInt("\nWhat length would you like your FizzBuzz to be? (1-1000): ");
        while (!Utilities.validSize(inputSize)) {
            inputSize = ScannerInput.validNextInt("\nThe length you entered is outside of the accepted range (1-1000), please enter a valid length: ");
        }
        pgmAPI.setFizzBuzzLength(inputSize);
        System.out.println("\nThe length has been set to " + inputSize);
    }

    private void printHelp() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("----------------- What is \"FizzBuzz\"? -----------------");
        System.out.println("-------------------------------------------------------");
        System.out.println("""
                            FizzBuzz is a programming challenge sometimes used in\s
                            job interviews. The candidate is required to write a
                            program that prints every number from 1 to 100. For
                            each number that is a multiple of 3, the program
                            should replace the number with the word Fizz; for each
                            number that is a multiple of 5, replace it with the
                            word buzz.
                            
                            The difficulty of this programming challenge comes in
                            the follow-up questions. A candidate may be required
                            to replace or even add two or three additional words
                            or change which numbers get replaced. If their attempt
                            is not efficient, their code could get very messy very
                            quickly. The purpose of the challenge is to test the
                            candidate's foresight and the general adaptability of
                            their code.
                            
                            The goal of this program is to serve as the ultimate
                            answer to all of these follow-up questions. Instead
                            of updating or refactoring the code to satisfy any
                            follow-up questions, this program offers a straight-
                            forward user interface that allows the user to modify
                            the simple game of FizzBuzz in any way they see fit,
                            without ever needing to touch the code.""");
        System.out.println("-------------------------------------------------------");
    }
}