/*
 * IT ### Final Project.
 * SaySomethingTest to test SaySomething class.
 * Jeffrey Cutter
 */
package sillygame;

import static sillygame.SaySomething.sayStuffCannedMessage;
import static sillygame.SaySomething.sayStuffRandomMessage;

/**
 * SaySomethingTest Class for unit testing SaySomething class.
 * @author jcutter
 */
public class SaySomethingTest {
	
	/**
	 * main method
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		// Use testCanned to test sayStuffCannedMessage for the major paths.
		testCanned("Select one of the predefined sentences.");
		testCanned("Define some user defined sentences, and then select one.");
		testCanned("Select a random sentence.");
		testCanned("Select a random sentence.");
		testCanned("Select a random sentence.");


		// Call sayStuffRandomMessage several times to test randomly generated sentences.

		/**
		 * An int to hold the number of times to call sayStuffRandomMessage.
		 */
		final int NUMBER_OF_TIMES_TO_RUN = 10;

		System.out.printf("%d Random Sentences:\n\n", NUMBER_OF_TIMES_TO_RUN);
		/*
		A loop to run sayStuffRandomMessage the defined number of times.
		*/
		for (int i=1; i<=NUMBER_OF_TIMES_TO_RUN; i++) {
			System.out.println(sayStuffRandomMessage());
		}

		System.out.println();

		System.out.printf("%d say somethings have been made.\n", SaySomething.getSaySomethingCount());


	}

	/**
	 * Test 
	 * @param instruction 
	 */
	private static void testCanned(String instruction) {

		// Give instruction.
		System.out.printf("### %s ###\n", instruction);

		// Call sayStuffCannedMessage to have the user follow the instruction.
		// Return is a String of the selected message.
		String mySentence = sayStuffCannedMessage();

		// Show output of the selected message.
		System.out.print("\n### Validate sentence choice: ###\n\n");
		System.out.printf("%s\n\n", mySentence);

	}

}
