package utils;

import java.util.List;

/**
 * Utilities class.
 *
 * @author Dean Lonergan
 * @version 0.0.8
 */
public class Utilities {

    /**
     * Method to test if a String input contains fewer than 10 characters.
     * @param string input String.
     * @return true if the length of the string is less than 10, false otherwise.
     */
    public static boolean max15Chars(String string) {
        return string.length() <= 15;
    }

    /**
     * Method to test if an int input contains only non-negative numbers.
     * @param num int number.
     * @return true if the number is non-negative, false otherwise.
     */
    public static boolean validIntNonNegative(int num) {
        return num >= 0;
    }

    /**
     * Method to test if an int input contains only positive numbers.
     * @param num int number.
     * @return true if the number is positive (greater than 0), false otherwise.
     */
    public static boolean validPositive(int num) {
        return num >= 1;
    }

    /**
     * Method to test if an int input contains only positive numbers.
     * @param num int number.
     * @return true if the number is positive (greater than 0), false otherwise.
     */
    public static boolean validSize(int num) {
        return num >= 1 && num <= 1000;
    }

    /**
     * Method to test if an int input is valid and matches a point in an Array.
     * @param index The Array index.
     * @param array The ArrayList.
     * @return true if the index is a valid point within the ArrayList, false otherwise.
     */
    public static boolean validIndex(int index, List<?> array) {
        return validIntNonNegative(index) && index < array.size();
    }

    /**
     * Method to test if the first letter of an input is y/Y.
     * @param input char input.
     * @return true of the first letter of the input is y or Y, false otherwise.
     */
    public static boolean validYesInput(char input) {
        return input == 'y' || input == 'Y';
    }

}