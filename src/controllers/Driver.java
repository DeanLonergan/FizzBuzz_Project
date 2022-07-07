package controllers;

import utils.ScannerInput;
import utils.Utilities;

/**
 * Driver class.
 *
 * @author Dean Lonergan
 * @version 0.0.9
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
        System.out.println(" (2) Custom FizzBuzz");
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
                        pgmAPI.classicFizzBuzz();
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
                        printAbout();
                    }
                    catch(Exception e){
                        System.err.println("Error printing about: " + e);
                    }
                    break;
            }
            ScannerInput.validNextLine("\nPress any key to continue...");
            option = mainMenu();
        }
        if (!pgmAPI.getWords().isEmpty() || pgmAPI.getFizzBuzzLength() != 100) {
            char input = ScannerInput.validNextChar("\nWould you like to save your custom FizzBuzz before exiting? (Y/N): ");
            if (Utilities.validYesInput(input)) {
                try{
                    pgmAPI.save();
                }
                catch(Exception e){
                    System.err.println("Error saving your fizzbuzz: " + e);
                }
                System.out.println("\nYour custom FizzBuzz has been saved!");
            }
        }
        System.out.println("\nExiting... bye");
    }

    private void runCustomMenu() {
        int option = pgmAPI.customMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    try{
                        System.out.println("\n-------------------------------------------------------");
                        System.out.println("------------------- Custom FizzBuzz -------------------");
                        System.out.println("-------------------------------------------------------");
                        System.out.println(pgmAPI.fizzBuzz());
                    }
                    catch(Exception e){
                        System.err.println("Error printing custom FizzBuzz: " + e);
                    }
                    break;
                case 2:
                    try{
                        runWordMenu();
                    }
                    catch(Exception e){
                        System.err.println("Error adding a word: " + e);
                    }
                    break;
                case 3:
                    try{
                        pgmAPI.changeLength();
                    }
                    catch(Exception e){
                        System.err.println("Error changing length: " + e);
                    }
                    break;
                case 8:
                    try{
                        pgmAPI.save();
                    }
                    catch(Exception e){
                        System.err.println("Error saving your fizzbuzz: " + e);
                    }
                    System.out.println("\nYour custom FizzBuzz has been saved!");
                    break;
                case 9:
                    try{
                        pgmAPI.load();
                    }
                    catch(Exception e){
                        System.err.println("Error loading custom fizzbuzz: " + e);
                    }
                    break;
            }
            ScannerInput.validNextLine("\nPress any key to continue...");
            option = pgmAPI.customMenu();
        }
    }

    private void runWordMenu() {
        int option = pgmAPI.wordMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    try{
                        pgmAPI.addWord();
                    }
                    catch(Exception e){
                        System.err.println("Error adding a word: " + e);
                    }
                    break;
                case 2:
                    try{
                        pgmAPI.updateWord();
                    }
                    catch(Exception e){
                        System.err.println("Error adding a word: " + e);
                    }
                    break;
                case 3:
                    try{
                        pgmAPI.removeWord();
                    }
                    catch(Exception e){
                        System.err.println("Error removing word: " + e);
                    }
                    break;
            }
            ScannerInput.validNextLine("\nPress any key to continue...");
            option = pgmAPI.wordMenu();
        }
    }

    private void printAbout() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("----------------- What is \"FizzBuzz\"? -----------------");
        System.out.println("-------------------------------------------------------");
        System.out.println("""
                FizzBuzz is a programming challenge sometimes used in\s
                job interviews. The candidate is required to write a
                program that prints every number from 1 to 100. For
                each number that is a multiple of 3, the program
                should replace the number with the word Fizz; for
                each number that is a multiple of 5, replace it with
                the word buzz.
                                            
                A candidate may be required to replace or add two or
                three additional words or change which numbers get
                replaced. If their attempt is not efficient, their
                code could get very messy very quickly. The purpose
                of the challenge is to test the candidate's foresight
                and the general adaptability of their code.
                                            
                The goal of this program is to answer these follow-up
                questions without updating or refactoring the code.
                The program offers a straightforward interface that
                allows the user to modify the simple game of FizzBuzz
                in any way they see fit, without ever needing to touch
                the code.""");
        System.out.println("-------------------------------------------------------");
    }
}