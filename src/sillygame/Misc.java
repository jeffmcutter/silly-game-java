/*
 * IT ### Final Project.
 * Misc Class to hold some common static methods.
 * Jeffrey Cutter
 */
package sillygame;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Misc Class - a place to put some handy common static methods.
 * @author jcutter
 */
public class Misc {
	
	/**
	 * Ask for and collect a yes or no answer.
	 * 
	 * @param question is a String for the yes/no question to be asked.
	 * @return a boolean, true for yes, false for no.
	 */
	public static boolean yesOrNo(String question) {
		/**
		 * A boolean to track if we have obtained a good answer yet.
		 */
		boolean goodAnswer = false;

		/**
		 * A boolean to capture our result in, default to false (no).
		 */
		boolean result = false;

		// Loop until we get a good answer.
		while ( goodAnswer != true ) {

			/**
		 	* A Scanner object in, to collect user input.
		 	*/
			Scanner in = new Scanner(System.in);

			// Ask the user the y/n question and solicit an answer.
			System.out.printf("%s (y/n) ", question);

			// Collect user input.
			String answer = in.nextLine();

			// Lowercase the answer to make it easier to check.
			answer = answer.toLowerCase();

			// If the user answers y or yes, case insensative, thanks to lowercasing the answer.
			if ( answer.equals("y") || answer.equals("yes") ) {
				// Capture our result as yes (true).
				result = true;
				// We got a valid answer, set goodAnswer to true.
				goodAnswer = true;
			// If the user answers n or no, that's a valid answer.
			} else if ( answer.equals("n") || answer.equals("no") ) {
				// result is already false by default, but why not.
				result = false;
				// We got a valid answer, set goodAnswer to true.
				goodAnswer = true;
			} else {
				// Inform the user of valid options, and we'll loop again.
				System.out.println("\nValid answers are y or yes or n or no.\n");
			}
		}
		return result;
	}

	/**
	 * Ask the provided question and collect and return an integer answer.
	 * @param question is a String containing the question to ask.  Provide your own punctuation at the end.
	 * @param low is an int of the lowest allowable value.
	 * @param high is an int of the highest allowable value.
	 * @return an int of the user's answer.
	 */
	public static int getIntAnswer(String question, int low, int high) {
		// boolean goodAnswer to control the while loop.
		boolean goodAnswer = false;
		// int answer to hold the answer.
		int answer = 0;
		// Loop until we get an integer.
		while ( goodAnswer == false ) {
			// A Scanner object to collect user input.
			Scanner in = new Scanner(System.in);
			// Ask the question.
			System.out.println(question);
			// Use try catch to ensure user provides an integer.
			try {
				// Collect the answer.
				answer = in.nextInt();
				// If the entered integer is outside the range.
				if ( answer < low || answer > high ) {
					// Let player know.
					System.out.println("Sorry, that number is not a valid choice.  Try again.");
				} else {
					goodAnswer = true; // And we're out.
				}
			} catch ( InputMismatchException e ) {
				// Ask user to try again.
				System.out.println("Your answer must be an integer.  Please try again.");
			}
		}
		return answer;
	}



	/**
	 * Given a list of Strings, print the list numbered starting at 1.
	 * @param list is the list to be printed.
	 */
	public static void printCountedList(List<String> list) {
		// An int to number with.
		int num = 1;

		// Print header.
		System.out.printf("\n%-8s Description\n", "Number");

		// Iterate over list and print them out.
		for (String line: list) {
			// Print this line.
			System.out.printf("%-8d %s\n", num, line);
			// Increment our number.
			num++;
		}
	}

}
