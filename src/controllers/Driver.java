package controllers;

import models.Word;
import utils.ScannerInput;
import utils.Utilities;

/**
 * Driver class.
 *
 * @author Dean Lonergan
 * @version 0.0.3
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
        System.out.println(" (2) Create your own (not working)");
        System.out.println("-------------------------------------------------------");
        System.out.println(" (9) Help");
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
                        System.err.println("Error entering the Custom FizzBuzz menu: " + e);
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
        System.out.println("Exiting... bye");
    }

    private int customMenu() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("------------------- Custom FizzBuzz -------------------");
        System.out.println("-------------------------------------------------------");
        System.out.println(" (1) Add a word");
        System.out.println(" (2) Remove a word");
        System.out.println(" (2) Change length (Current: 100)");
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
                        classicFizzBuzz();
                    }
                    catch(Exception e){
                        System.err.println("Error printing classic FizzBuzz (that's a big problem): " + e);
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
        if (!pgmAPI.getWords().isEmpty()) {
            char input = ScannerInput.validNextChar("\nWould you like to save your custom FizzBuzz? (Y/N): ");
            if (Utilities.validYesInput(input)) {
                System.out.println("\nYour custom FizzBuzz has been saved.");
                ScannerInput.validNextLine("Press any key to continue...");
            }
        }
        runMainMenu();
    }

    private void printHelp() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("----------------- What is \"FizzBuzz\"? -----------------");
        System.out.println("-------------------------------------------------------");
        System.out.println("FizzBuzz is a programming task used in job interviews.");
        System.out.println("""
                            A candidate is asked to write a program that prints\s
                            every number from 1 to 100. For each number that is a
                            multiple of 3, the program should replace the number
                            with the word “Fizz”, and for each number that is a
                            multiple of 5 it should replace with the word "buzz".""");
        System.out.println("-------------------------------------------------------");
        System.out.println("""
                            This program was inspired by this task. Many of the\s
                            follow-up questions require the interviewee to replace
                            the words “Fizz” or “Buzz” with alternatives, change
                            the multiples which they replace, or add entirely new
                            words of new multiples. They may be asked to do any
                            number of things to what would otherwise seem to be
                            a rather simple and straightforward program.""");
        System.out.println("-------------------------------------------------------");
        System.out.println("""
                            The goal of this program is serve as the ultimate\s
                            answer to all of these follow up questions. Instead of
                            updating or refactoring the code the satisfy any follow
                            up questions, this program offers a straight-forward
                            user interface which allows the user to modify the
                            simple game of "FizzBuzz" in any way they see fit,
                            without ever needing to touch the code.""");
    }

    public void classicFizzBuzz() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("------------------ Classic FizzBuzz -------------------");
        System.out.println("-------------------------------------------------------");
        pgmAPI.addWords(new Word("Fizz", 3));
        pgmAPI.addWords(new Word("Buzz", 5));
        pgmAPI.setSize(100);
        System.out.println(pgmAPI.fizzBuzz());
    }

    public void fizzBuzzSize() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("------------------- Custom FizzBuzz -------------------");
        System.out.println("-------------------------------------------------------");
        int size = ScannerInput.validNextInt("\nHow large you like your FizzBuzz to be? (1-1000): ");
        if (Utilities.validSize(size)) {
            pgmAPI.setSize(size);
        }
    }
}