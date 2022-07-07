package utils;
import java.util.Scanner;

/**
 * Scanner Input class.
 *
 * @author Dean Lonergan
 * @version 0.1.0
 */
public class ScannerInput {

    /**
     * Method to take in valid user input in the form of an integer.
     * @param prompt a relevant text prompt is displayed to the user, requesting an int input.
     * @return the users valid input.
     */
    public static int validNextInt(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.print(prompt);
                return Integer.parseInt(input.next());
            }
            catch (NumberFormatException e) {
                System.err.println("\tInvalid input - Please enter a number.");
            }
        }  while (true);
    }

    /**
     * Method to take in valid user input in the form of a String.
     * @param prompt a relevant text prompt is displayed to the user, requesting a String input.
     * @return the users valid input.
     */
    public static String validNextLine(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();

    }

    /**
     * Method to take in valid user input in the form of the first character of a String.
     * @param prompt a relevant text prompt is displayed to the user, requesting a String.
     * @return the users valid input.
     */
    public static char validNextChar(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.next().charAt(0);
    }
}
