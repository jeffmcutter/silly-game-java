/*
 * IT ### Final Project
 * SillyGame Class.
 * Jeffrey Cutter
 */
package sillygame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Class SillyGame runs the Silly Game simulator / game.
 * Makes use of all of the classes (non test) in our sillygame package.
 * @author jcutter
 */
public class SillyGame {


	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		/**
		 * An ArrayList to hold our Participant MyCreate objects.
		 */
		List<MyCreate> players = new ArrayList<>(0);

		/**
		 * An int to track how many rounds have been played.
		 */
		int roundsPlayed = 0;
		
		// If the game is new, or if there are still players in the game,
		// continue to play the game in a loop.
		do {

			// Offer players to join.
			// Pass in current List of players.
			// Receive back new List of players.
			players = offerJoin(players);

			// If we have no players, exit.
			if ( players.size() < 1 ) {
				System.out.println("Looks like no one wants to play this time.");
				System.exit(0);
			}

			// Tracks if we did an introduction for spacing.
			boolean hadIntroductions = false;

			// Introduce everyone.
			for (MyCreate player: players) {
				boolean wasIntroduced = player.introduction();
				if ( wasIntroduced == true ) {
					hadIntroductions = true;
				}
			}

			// Print new line if we did an intro only.
			if ( hadIntroductions == true ) {
				System.out.println();
			}

			// Offer and serve beverages to players.
			// Capture count of how many were served.
			int served = offerBeverages(players);

			// Add a new line if anyone was served.
			if ( served > 0 ) {
				System.out.println();
			}

			// Offer conversation.
			// Capture count of say something's.
			int saySomethings = offerConversation(players);

			// Add a new line if anyone said something.
			//if ( saySomethings > 0 ) {
			//	System.out.println();
			//}

			// Increment the number of rounds played.
			roundsPlayed++;

			// Summarize the current game.
			printSummary(players.size(), roundsPlayed);

			// Offer players to leave game.
			// Pass in current List of players.
			// Receive back new List of players.
			players = offerLeave(players);

		} while ( players.size() > 0 );

		// No players left, end the game.
		System.out.println("\nLooks like everyone has gone home!");

		printSummary(players.size(), roundsPlayed);
	}

	/**
	 * Offer players to join the game.
	 * @param players is the current List of MyCreate players.
	 * @return is the updated list of MyCreate players.
	 */
	public static List offerJoin(List players) {
		/**
		 * A boolean to track if anyone wants to join the game this round.
		 */
		// Find out if anyone wants to enter the silly game..		
		boolean someoneWantsIn = Misc.yesOrNo("\nIs there anyone who would like to join in the Silly Game?");
		if ( someoneWantsIn == true || players.size() > 0 ) {
			System.out.println();
		}

		// Track if somone joined this round for spacing.
		boolean someoneJoined = someoneWantsIn;

		// As long as another person wants in, keep collecting players.
		while ( someoneWantsIn == true ) {

			// Collect new persons info and add to players ArrayList.
			players.add(inputPlayer());

			// See if anyone else wants in.
			someoneWantsIn = Misc.yesOrNo("\nDoes anyone else want to join the game?");

			// Print out a newline if someoneWantsIn.
			if ( someoneWantsIn == true ) {
				System.out.println();
			}
		}

		// Only need space here if someone joined.
		if ( someoneJoined == true ) {
			System.out.println();
		}
		return players;
	}

	public static List offerLeave(List players) {

		// Offer to remove players.
		boolean someoneWantsOut = Misc.yesOrNo("\nDoes anyone want to leave the Silly Game before the next round?");

		// While someone wants to leave the game.
		while ( someoneWantsOut == true ) {

			/**
			 * An int to track which user wants to leave the game.
			 */
			int leavingUser = getUser(players, "Enter the number next to the info of the user who wishes to leave:");

			// Say goodbye to player.
			MyCreate thisClone = (MyCreate) players.get(leavingUser);
			System.out.println();
			thisClone.sayGoodbye();

			// Remove the selected player.
			players.remove(leavingUser);

			// If players List is Empty.
			if ( players.size() > 0 ) {
				// Does anyone else want to leave?
				someoneWantsOut = Misc.yesOrNo("\nDoes anyone else want to leave the Silly Game before the next round?");
			} else {
				someoneWantsOut = false; // end the loop.
			}
		}
		return players;
	}

	/**
	 * Method inputPlayer collects user info.
	 * @return a MyCreate object containing the user info.
	 */
	public static MyCreate inputPlayer() {
		// Initialize a MyCreate object to return.
		MyCreate clone = null;

		// A boolean to base our loop on
		boolean success = false;

		// In a loop until we have success.
		while ( success != true ) {
			// Allow MyCreate instantiation/setters to handle validation when possible.

			// A Scanner object in, to collect user info.
			Scanner in = new Scanner(System.in);

			// Collect firstName.
			System.out.println("What is your first name?");
			String firstName = in.nextLine();

			// Collect LastName.
			System.out.println("What is your last name?");
			String lastName = in.nextLine();
			
			// Collect age.
			int age = Misc.getIntAnswer("How old are you?", 0, 120);

			// Collect gender.
			System.out.println("Are you male or female?");
			String gender = in.nextLine();

			// Collect if user thinks they like beer or not.
			// Since this needs to be a boolean and it's a yes or no question, use yesOrNo.
			boolean likesBeer = Misc.yesOrNo("Do you find that you like beer?");

			// Instantiate MyCreate object in a try catch to deal
			// with invalid parameters being passed.
			try {
				clone = new MyCreate(firstName, lastName, age, gender, likesBeer);
				// Got here so no exception was thrown, success!
				success = true;
				
			} catch (IllegalArgumentException e) {
				System.out.printf("%s  Please try again.\n", e.getMessage());
			}
		}
		return clone;
	}

	
	/**
	 * A method to offer and serve beverages to any MyCreate's in the players List.
	 * @param players is a List of MyCreates to serve.
	 * @return an int of the number of beverages served.
	 */
	public static int offerBeverages(List players) {

		// Make a copy of players so we can make temporary changes to the List for tracking
		// who has already placed their drink order.
		List playersCopy = new ArrayList(players);

		/**
		 * An List to track beverage order for anyone who wants one.
		 * List to contain another List which will have MyCreate as index 0
		 * and Beverage as index 1.
		 */
		// Using a List to be able to serve beverages in the order they were requested.
		List<List> beverageOrders = new ArrayList<>(players.size());

		/**
		 * A boolean to track if there is anyone who wants a drink.
		 */
		// Find out if anyone wants a drink.
		boolean someoneWantsBeverage = Misc.yesOrNo("Does anyone want a beverage?");

		// In a loop collect drink order from anyone who wants one.
		// Assumes at least one person wants one since someone said yes to enter the loop.
		while ( someoneWantsBeverage == true ) {

			/**
			 * An int to track who is currently placing drink order.
			 */
			// Get the number of the user to take an order.
			int thirstyUser = getUser(playersCopy, "Enter the number next to your name if you would like to order a drink:");

			/**
			 * A Beverage object to hold this order.
			 */
			// Take drink order and store it temporarily in thisOrder.
			List thisOrder = inputBeverage((MyCreate) playersCopy.get(thirstyUser));

			// inputBeverage returns a List with the first item a Boolean for iWillPass condition.
			if ( thisOrder.get(0).equals(false) ) {
				/**
			 	* A List to hold the MyCreate object at index 0 and
			 	* the Beverage object at index 1.
			 	*/
				List thisList = new ArrayList(2);

				// User placed an order since iWillPass was false.
				// Add this MyCreate player to thisList.
				thisList.add(playersCopy.get(thirstyUser));
				// Add this drink order to thisList.
				thisList.add(thisOrder.get(1));
				// Add thisList to beverageOrders.
				beverageOrders.add(thisList);
			}

			// Remove user from the playersCopy array list since they can only order once per round.
			playersCopy.remove(thirstyUser);


			// If playersCopy is empty.
			if ( playersCopy.isEmpty() ) {
				someoneWantsBeverage = false; // Everyone has placed a drink / no more orders.
			} else {
				// Does anyone else want a beverage?
				someoneWantsBeverage = Misc.yesOrNo("\nDoes anyone else want a beverage?");
			}
		}

		System.out.println(); // Nice spacing.

		// Iterate over the beverageOrders List and serve people their beverages.
		for ( List list: beverageOrders ) {

			// Extract the MyCreate object from List list.
			MyCreate clone = (MyCreate) list.get(0);
			
			// Extract the Beverage object from List list.
			Beverage thisBeverage = (Beverage) list.get(1);

			// Address the person we are serving.
			System.out.printf("%s %s: ", clone.getFirstName(), clone.getLastName());
			
			// Serve this clone's beverage.
			thisBeverage.serveBeverage();
		}

		return beverageOrders.size();
	}


	/**
	 * Method inputBeverage collects beverage order from the player.
	 * @param player is a MyCreate object of the player ordering.
	 * @return a List with the first object containing a Boolean for iWillPass and the second object containing a Beverage object.
	 */
	public static List inputBeverage(MyCreate player) {
		// Initialize an ArrayList of objects for our multiple return.
		List<Object> returnList = new ArrayList<>();

		//  Initialize a Beverage object to return.
		Beverage beverage = null;

		// Initialize a Boolean to hold our I'll pass value.
		Boolean iWillPass = false;

		// A Scanner object in, to collect user info.
		Scanner in = new Scanner(System.in);

		// A List to hold our choices here.
		List<String> drinkChoices = new ArrayList<>(Arrays.asList("Beer", "Tea", "No thanks, I'll pass."));

		// Print our list of drink choices.
		Misc.printCountedList(drinkChoices);

		
		// Find out what sort of drink our clone would like.
		int beverageType = Misc.getIntAnswer("\n" + player.getFirstName() + " " +  player.getLastName() + ", what would you like to drink?", 1, 3);

		// If it's beer;
		if ( beverageType  == 1 ) {
			
			// Print a header row for our listing of beer styles.
			System.out.printf("\n%-10s %-30s %5s\n", "Number", "Description", "ABV");

			// Get the available beerStyles HashMap.
			Map<String, Float> beerStyles = Beer.getBeerStyles();

			// An ArrayList to be able to recall beer style by number.
			List<String> beerStylesIndex = new ArrayList<>(beerStyles.size());

			// Iterate over the beer styles.
			int num = 1; // So we can print a number to choose by.
			for ( String beer: beerStyles.keySet() ) {
				// Add this beer to the beerStylesIndex.
				beerStylesIndex.add(beer);
				// Print number to choose by, beer style, abv.
				System.out.printf("%-10d %-30s %5.1f\n", num, beer, beerStyles.get(beer));
				// Increment number to choose by.
				num++;
			}

			// Collect our answer.
			int beerStyle = Misc.getIntAnswer("\nPlease choose the style of beer you would like:", 1, num);

			// Get the superclass Beverage details.
			List beverageDetails = getBeverageDetails();

			// Create our Beer with the collected values.
			beverage = new Beer(
				(String) Beer.getValidContainers().get((int) beverageDetails.get(0) - 1),
				(String) Beer.getValidTemperatures().get((int) beverageDetails.get(1) - 1),
				(boolean) beverageDetails.get(2),
				(String) beerStylesIndex.get(beerStyle - 1)
			);

		// If it's tea.
		} else if ( beverageType == 2 ) {

			// List the types of tea we have to choose from with number.
			Misc.printCountedList(Tea.getTeaTypes());
			
			// Collect the tea type this clone would like.
			int teaType = Misc.getIntAnswer("\nPlease choose the type of tea you would like:", 1, Tea.getTeaTypes().size());

			// Get the superclass Beverage details.
			List beverageDetails = getBeverageDetails();

			// Create our Tea with the collected values.
			beverage = new Tea(
				(String) Tea.getValidContainers().get((int) beverageDetails.get(0) - 1),
				(String) Tea.getValidTemperatures().get((int) beverageDetails.get(1) - 1),
				(boolean) beverageDetails.get(2),
				(String) Tea.getTeaTypes().get(teaType - 1)
			);

		// If player decided to pass, set iWillPass to true.
		} else if ( beverageType == 3 ) {
			iWillPass = true;
		}

		// Add our two return items to the returnList in the correct order.
		returnList.add(iWillPass);
		returnList.add(beverage);

		return returnList;
	}


	/**
	 * Get the beverage superclass common details from the user.
	 * @return a List in the format of String container, String temperature, boolean onTheRocks.
	 */
	public static List getBeverageDetails() {

		// Print header for this question.
		//System.out.println("\nAvailable containers");
		//System.out.println("--------------------");
		// Print numbered list of choices.
		Misc.printCountedList(Beverage.getValidContainers());
		// Capture answer by number.
		int container = Misc.getIntAnswer("\nWhat is the number of the type of container would you like?", 1, Beverage.getValidContainers().size());

		// Print header for this question.
		//System.out.println("\nAvailable temperatures");
		//System.out.println("----------------------");
		// Print numbered list of choices.
		Misc.printCountedList(Beverage.getValidTemperatures());
		// Capture answer by number.
		int temperature = Misc.getIntAnswer("\nWhat is the number of the temperature would you like?", 1, Beverage.getValidTemperatures().size());
		
		// Determine if clone wants ice.
		boolean onTheRocks = Misc.yesOrNo("\nWould you like ice with that?");

		// Add the results to our ArrayList.	
		List results = new ArrayList();
		results.add(container);
		results.add(temperature);
		results.add(onTheRocks);

		return results;
	}

	/**
	 * A method to offer conversation and perform say something's.
	 * @param players is a List of MyCreates to serve.
	 * @return an int of the number of say something's performed.
	 */
	public static int offerConversation(List players) {

		// Make a copy of players so we can make temporary changes to the List for tracking
		// who has already placed their drink order.
		List playersCopy = new ArrayList(players);

		/**
		 * A List of Lists to store conversation requests in order for anyone who wants to say something's.
		 * The inner lists will store MyCreates object at index 0 and String sentence at index 1.
		 */
		List<List> saySomethingRequests = new ArrayList<>(players.size());

		/**
		 * A boolean to track if there is anyone who wants to speak.
		 */
		// Find out if anyone wants to speak.
		boolean someoneWantsToSpeak = Misc.yesOrNo("Does anyone want to speak?");

		// In a loop collect say something requests from anyone who wants to speak.
		// Assumes at least one person wants to speak since someone said yes to enter the loop.
		while ( someoneWantsToSpeak == true ) {
			/**
			 * An int to track who is currently submitting a saySomething request.
			 */
			// Get the number of the user to take an order.
			int speaker = getUser(playersCopy, "Enter the number next to your info if you would like to speak:");

			/**
			 * An List to hold the responses for what type of sentences to use.
			 */
			List<String> sentenceTypes = new ArrayList<>(Arrays.asList("I would like to choose from a list of canned sentences.", "I would like a sentence made up from random words"));
			// Print out the available sentence types.
			Misc.printCountedList(sentenceTypes);

			/**
			 * An int to hold the desired sentence type.
			 */
			// Determine the desired sentence type.
			int sentenceType = Misc.getIntAnswer("\nHow would you like to speak?", 1, sentenceTypes.size());

			/**
			 * A List to hold MyCreate Object at index 0 and String sentence at index 1.
			 */
			List thisList = new ArrayList(2);
			// Store the MyCreate object in thisList at index 0.
			thisList.add(playersCopy.get(speaker));
			
			// If sentenceType is canned.
			if (sentenceType == 1 ) {
				// Run sayStuffCannedMessage and store the result in thisList at index 1.
				thisList.add(SaySomething.sayStuffCannedMessage());
			} else {
				// Run sayStuffRandomMessage and store the result in thisList at index 1.
				thisList.add(SaySomething.sayStuffRandomMessage());
			}

			// Add thisList to saySomethingRequests.
			saySomethingRequests.add(thisList);
			// Remove this player as they can only say something once per round.
			playersCopy.remove(speaker);

			// If there are still players who haven't had opportunity.
			if ( playersCopy.size() > 0 ) {
				// Find out if anyone else wants to speak.
				someoneWantsToSpeak = Misc.yesOrNo("\nDoes anyone else want to speak?");
			} else {
				someoneWantsToSpeak = false;
			}
		}

		// If we have say something's to do, put in a new line.
		if ( saySomethingRequests.size() > 0 ) {
			System.out.println();
		}

		// Iterate over the saySomethingRequests received, and serve up the messages.
		for ( List list: saySomethingRequests ) {

			// Extract clone from this list.
			MyCreate clone = (MyCreate) list.get(0);

			// Extract sentence from this list.
			String sentence = (String) list.get(1);

			// Print what this player has to say.
			System.out.printf("%s %s says: \"%s\"\n", clone.getFirstName(), clone.getLastName(), sentence);
		}

		return saySomethingRequests.size();
	}

	/**
	 * List out the players in the provided ArrayList of MyCreates.
	 * @param players an ArrayList of MyCreates.
	 */
	public static void listPlayers(List players) {
		System.out.printf("\n%-15s %s\n", "Player Number", "Player Details");
		for (int i=0; i < players.size(); i++ ) {
			MyCreate clone = (MyCreate) players.get(i);
			System.out.printf("%-15d %s\n", i+1, clone.list());
		}
	}

	/**
	 * Collect who wants to participate in the question.
	 * @param players is a List of MyCreate Players.
	 * @param question a string containing the question to ask.
	 * @return an int of which index from the List was selected.
	 */
	public static int getUser(List players, String question) {

		// An int to track which player is ordering a drink.
		int user = -1;

		// Print all players. 
		// Numbers start at 1.
		listPlayers(players);
		System.out.println(); // Nice spacing.
				

		// In a loop, get the user.
		// Stay in loop to get right answer so long as answer is <= 0
		// or answer is greater than size.
		while ( user <= 0 || user > players.size() ) {
			// Find out who wants wants this.
			user = Misc.getIntAnswer(question, 1, players.size());

		}
		return user - 1; // Minus 1 will return the ArrayList index since listPlayers stats at 1.
	}

	/**
	 * Prints out a summary of the game at the current moment.
	 * @param currentPlayerCount
	 * @param roundsPlayed
	 */
	public static void printSummary(int currentPlayerCount, int roundsPlayed) {
		// Print game statistics.
		System.out.println("\nGame Activity Summary:");
		// Number of rounds played.
		System.out.printf("%d rounds have been played.\n", roundsPlayed);
		// Current number of players.
		System.out.printf("%d players are currently in the game.\n", currentPlayerCount);
		// Total number of joins to the game.
		System.out.printf("%d joins to the game.\n", MyCreate.getNumberOfClones());
		// This is inherited from Beverage superclass.
		System.out.printf("%d beers served.\n", Beer.getBeersServed());
		// This is inherited from Beverage superclass.
		System.out.printf("%d teas served.\n", Tea.getTeasServed());
		// Print a summary of how many beverages and which type were served.
		System.out.printf("%d total beverages served.\n", Beverage.getBeveragesServed());
		// Print out a summary of how many say somethings occurred.
		System.out.printf("%d total say somethings delivered.\n", SaySomething.getSaySomethingCount());
	}
	
}
