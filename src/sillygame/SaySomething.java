/*
 * IT ### Final Project
 * SaySomething Class!
 * Jeffrey Cutter
 */
package sillygame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * SaySomething class for inclusion in the Silly Game project.
 * Used to "say something" messages.
 * @author jcutter
 */
public class SaySomething {
	
	/**
	 * An int to hold the count of the number of sayStuff returned.
	 */
	private static int sayStuff;

	/**
	 * Define 10 messages / sentences, display them to user and allow user to
	 * select the sentence of their liking or randomly select a canned sentence.
	 * @return a String of the selected message.
	 */
	public static String sayStuffCannedMessage() {

		/**
		 * Get a random object to use for selecting random integers.
		 */
		Random random = new Random();

		/**
		 * A HashMap called sentences.
		 * Keys are Strings and Values are Strings.
		 * Initial capacity of 10 key/value pairs.
		 */
		Map<String, String> sentences = new HashMap<>(10);

		// Add required 10 key value pairs to sentences.
		sentences.put("beer1", "Ale is often better served at cellar temperatures than ice cold.");
		sentences.put("beer2", "Traditional English Real Ale is naturally carbonated and served without forced CO2 or other beer gas.");
		sentences.put("beer3", "Casks are traditionally kept in the cellar where it's cooler, think before refrigeration.");
		sentences.put("beer4", "Optimum cellar temperature for cask ale is considered to be about 55 degrees Fahrenheit.");
		sentences.put("beer5", "Ale naturally conditioned in bottles is also considered to be Real Ale.");
		sentences.put("tea1", "Sun brewed tea is potentially dangerous as bacteria can grow in warm temperatures.");
		sentences.put("tea2", "Refrigerator tea is basically the same as sun tea except it is steeped in the refrigerator.");
		sentences.put("tea3", "Colder temperatures in the refrigerator prevent growth of bacteria in the tea.");
		sentences.put("tea4", "To make refrigerator tea, put 2 to 4 tea bags per quart cold water into a container, cover and refrigerate.");
		sentences.put("tea5", "Steep the refrigerator tea for 6 hours up to a few days, open and enjoy.");

		sentences.put("random", "Randomly select one of the sentences above.");
		sentences.put("user", "Add your own sentence to the list.");

		/**
		 * A String to hold the answer.
		 */
		String answer = "";
		
		/**
		 * A Boolean success to track if we have received valid user input.
		 */
		boolean success = false;

		/**
		 * A TreeMap of sentences so that keys will be sorted by the key for printing.
		 */
		Map<String, String> sortedSentences = new TreeMap<>();

		/**
		 * An int to track the number of user entered sentences.
		 */
		int userSentenceCount = 0;

		// Collect user input in a while loop until we get a valid response.
		while ( ! success ) {

			/**
			 * A scanner object named in to collect user input with.
			 */
			Scanner in = new Scanner(System.in);

			// Print message for user that we are about to summarize the entered sentences.
			System.out.printf("\n%-10s %s\n", "Label", "Sentence");
			System.out.printf("%-10s %s\n", "---------", "------------------------------------------------------------------------------------------------------------");

			/**
		 	* Update sortedSentences TreeMap here so as to include any newly added
			* sentences on each trip through the loop.
		 	*/
			sortedSentences = new TreeMap<>(sentences);

			// Loop over the TreeMap sentences with an enhanced for loop.
			for ( String key : sortedSentences.keySet() ) {

				// Skip random and user entres so we can print them last.
				if ( ! key.equals("random") && ! key.equals("user") ) {
					// Print the rest of the sentence as we pass through the loop.
					// Including user defined sentences.
					System.out.printf("%-10s %s\n", key, sortedSentences.get(key));
				}
			}

			// print random and user entries.
			System.out.printf("\n%-10s %s\n", "random", sortedSentences.get("random"));
			System.out.printf("\n%-10s %s\n", "user", sortedSentences.get("user"));
			System.out.printf("%-10s %s\n", "---------", "------------------------------------------------------------------------------------------------------------");

			// Ask the user to choose a sentence.
			System.out.print("\nPlease enter the label of your choice: \n");

			// Collect user response.
			answer = in.nextLine();

			// Lowercase answer.
			answer = answer.toLowerCase();
					
			// If the user selected user to add their own sentence.
			if ( answer.equals("user")) {
				// Ask for the user defined sentence.
				System.out.println("\nPlease enter your sentence to add to the list:");
				// Get the user defined sentence.
				String userSentence = in.nextLine();
				// Verify the user defined sentence.
				System.out.print("\nYou entered the sentence:\n\n");
				System.out.printf("%s\n\n", userSentence);
				// Call static method yesOrNo from SillyGame to get the response to our question.
				boolean userConfirmed = Misc.yesOrNo("Is this what you want to add?");

				// If the user confirmed the response.
				if ( userConfirmed == true ) {
					// Increment the userSentenceCount since we have a successful sentence to add.
					userSentenceCount++;
					// Convert userSentenceCount to a String.
					String userSentenceCountString = Integer.toString(userSentenceCount);
					// Concatenate the userSentenceCount into a string we can use as a key.
					String userKey = "user" + userSentenceCountString;
					// Add the user's defined sentence to the sentences HashMap.
					sentences.put(userKey, userSentence);
				} else {
					// Let user know to try again.
					System.out.print("No?  Ok, please try again...\n\n");
				}
				// And we're going through the loop again.


			// If lowercased answer is one of the keys in sortedSentences...
			} else if ( sortedSentences.containsKey(answer) ) {
				// Set success to true so that loop will end.
				success = true;
			} else {
				// Else, provide notice if answer is not one of the valid labels (keys).
				System.out.print("Error:  Please choose one of the labels listed.\n");
				// And the loop will continue.
			}
		}

		// We must have a valid answer now.

		/**
		 * String sentence to hold the selected or randomly chosen canned sentence.
		 */
		String sentence;

		// If the user chose random choice, randomly select one of the canned sentences.

		if ( answer.equals("random") ) {
			// Take random out of the possible sentences.
			sentences.remove("random");

			/**
		 	* An ArrayList to hold our HashMap keys for random selection.
		 	*/
			List<String> keys = new ArrayList<>(sentences.keySet());

			// Next randomly select one of those keys from the keys ArrayList.
			String key = keys.get(random.nextInt(keys.size()));

			// Store the value for this key into sentence.
			sentence = sentences.get(key);
		} else {
			// User did not choose a random selection.
			// Grab the user selected sentence and store in sentence.
			sentence = sortedSentences.get(answer);
		}

		// Increment sayStuff tracker.
		sayStuff++;

		// return sentence.
		return sentence;

	}

	/**
	 * Generate a random message of the format subject - verb - adjective - object - adverb.
	 * @return a String containing the randomly generated sentences.
	 */
	public static String sayStuffRandomMessage() {
		/**
		 * Get a random object to use for selecting random integers.
		 */
		Random random = new Random();

		/**
		 * A HashMap of ArrayLists of Strings to hold lists of words to randomly select from by subject.
		 */
		Map<String, ArrayList<String>> wordLists = new HashMap<>();

		// Assign values to the ArrayLists in the HashMap by subject (key).
		// The HashMap isn't really needed with our static sentence makeup, however,
		// this would be handy if the sentence took other forms.
		wordLists.put("subjects", new ArrayList<>(Arrays.asList("I", "You", "Fred", "Janis", "They", "We", "Rorschach", "Roy", "Maurice", "Jen", "Douglas", "Denholm", "Richmond")));
		wordLists.put("verbs", new ArrayList<>(Arrays.asList("studying", "eating", "sneezing", "coughing", "drinking", "driving", "programming", "testing", "cooking", "cleaning")));
		wordLists.put("adjectives", new ArrayList<>(Arrays.asList("funny", "prickly", "hard", "awesome", "posh", "poncy", "tasty", "plucky", "whiffy", "cheeky", "slippy", "fiddly", "ignorant")));
		wordLists.put("objects", new ArrayList<>(Arrays.asList("course", "homework", "beer", "tea", "caravan", "bits", "mate", "bloke", "chap", "saloon", "boot", "bonnet", "lot")));
		wordLists.put("adverbs", new ArrayList<>(Arrays.asList("quickly", "badly", "blindly", "elegantly", "bravely", "daftly", "nervously", "tediosly", "wildly", "silently")));

		/**
		 * An ArrayList containing the makeup of our sentence.
		 * subject - verb - adjective - object - adverb.
		 */
		ArrayList<String> wordTypeOrder = new ArrayList<>(Arrays.asList("subjects", "verbs", "adjectives", "objects", "adverbs"));

		/**
		 * A String to hold our randomly selected sentence.
		 */
		String sentence =  "";

		// Loop over our wordTypeOrder and randomly select a word from each wordList type (key).
		// Add each random word to sentence with proper spacing.
		// Random is used for each pass so we get words from different indexes for each category.
		for (String wordType: wordTypeOrder) {

			/**
			 * Capture our random word in String word for just a moment.
			 */
			String word = wordLists.get(wordType).get(random.nextInt(wordLists.get(wordType).size()));

			// If sentence is still "".
			if (sentence.equals("")) {
				// Just add the word to it.
				sentence += word;
			} else {
				// Else, add a space, then the word.
				sentence += " " + word;
			}
		}

		// Add a period to the end of our sentence.
		sentence += ".";

		// Increment sayStuff tracker.
		sayStuff++;

		// Return our random sentence.
		return sentence;

	}

	/**
	 * Gives the number of say something's which have been given.
	 * @return an int for the purpose.
	 */
	public static int getSaySomethingCount() {
		return sayStuff;
	}
	
}
