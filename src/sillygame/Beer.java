/*
 * IT ### Final Project.
 * Subclass of additional class Beverage.
 * Because I wanted to try some inheritence.
 */
package sillygame;

import java.util.HashMap;
import java.util.Map;

/**
 * Beer Class, extends Beverage class.
 * @author jcutter
 */
public class Beer extends Beverage {

	/*
	Class variables - shared by the class, not specific to a particular object.
	Private to protect them.
	*/

	/**
	 * Map of Beer Styles, a String, to ABV (Alcohol by Volume), a Double.
	 * Specific types of beer available.
	 * Validation of beerStyle will be performed using these.
	 */
	// Static because it's common to all objects of the class.
	private static final Map<String, Double> beerStyles = new HashMap<>();
	// A static initializer to load our class beerStyles hashMap.
	static {
		beerStyles.put("American Lager", 5.5);
		beerStyles.put("English Bitter", 4.1);
		beerStyles.put("English Blonde Ale", 4.7);
		beerStyles.put("American West Coast IPA", 7.1);
		beerStyles.put("Barley Wine", 9.9);
		beerStyles.put("Gingerbread Ale", 6.5);
	}

	/**
	 * A class var to track how many beers have been served.
	 */
	private static int beersServed = 0;

	/*
	Instance variables.
	*/

	/**
	 * Beer Style.  Restricted to one of keys of HashMap beerStyles.
	 */
	private String beerStyle;

	/*
	Constructors.
	*/

	/**
	 * No parameters Beer constructor.  Sets defaults.
	 * 
	 * @throws IllegalArgumentException  if received from parameterized constructor.
	 */
	public Beer() throws IllegalArgumentException {
		// Call the no parameters constructor in the Beverage class.
		super();
		// Set beerStyle.
		setBeerStyle("American Lager");
	}

	/**
	 * Beer constructor accepting the required parameters.
	 * 
	 * @param container is a String - From Beverage and validated there.
	 * @param temperature is a string - From Beverage and validated there.
	 * @param onTheRocks is a boolean - From Beverage.
	 * @param beerStyle is a String with valid values specified in beerStyles keys.
	 * @throws IllegalArgumentException  if validation fails in the setters.
	 */
	public Beer(String container, String temperature, boolean onTheRocks, String beerStyle) throws IllegalArgumentException {
		// Call the constructor in the Beverage class.
		super(container, temperature, onTheRocks);
		// Set beerStyle.
		setBeerStyle(beerStyle);
	}

	/*
	Public Methods.
	*/

	/**
	 * Serve this beverage.
	 * Overrides serveBeverage from Beverage.
	 */
	@Override
	public void serveBeverage() {
		// If on the rocks.
		/*
		if (this.getOnTheRocks() == true) {
			// Say so.
			System.out.printf("That's pretty unusual having %s on the rocks.\n", this.getBeerStyle());
		}
		*/

		// Serve our beverage by printing out different messages based on how the drink was ordered.
		if ( this.getOnTheRocks() == true ) {
			System.out.printf("Here is your %s in a %s, served %s with ice in it, you goof ball.\n", this.getBeerStyle(), super.getContainer(), super.getTemperature());
		} else if ( this.getTemperature().equals("hot") ) {
			System.out.printf("Here is your %s in a %s, served %s, you nutter.\n", this.getBeerStyle(), super.getContainer(), super.getTemperature());
		} else if ( this.getTemperature().equals("warm") ) {
			System.out.printf("Here is your %s in a %s, served at cellar temperature (not really warm), an excellent choice.\n", this.getBeerStyle(), super.getContainer());
		} else {
			System.out.printf("Here is your %s in a %s, served nice and %s.\n", this.getBeerStyle(), super.getContainer(), super.getTemperature());
		}

		// Give an FYI about ABV of this particular beer.
		// % is escaped with % to actually print a %.
		//System.out.printf("An FYI if you are driving, the %s contains %.1f%% alcohol by volume.\n", this.getBeerStyle(), this.getBeerABV());
		
		// Increment number of beverages served.
		// This is in super, but is only protected, not private, so we can do this here.

		// Increment our Served counters.
		beersServed++;
		// This is in super but set protected, not private, so we can do it.  Wanted to try this.
		beveragesServed++;

	}

	/*
	Setters / Mutators.
	*/

	/**
	 * Set the beerStyle
	 * 
	 * @param beerStyle is a String.
	 * @throws IllegalArgumentException if beerStyle choice is not one of the keys in (available) beerStyles.
	 */
	public final void setBeerStyle(String beerStyle) throws IllegalArgumentException {
		// If the beerStyle choice is one of the available beerStyles.
		if (beerStyles.containsKey(beerStyle)) {
			// Use it.
			this.beerStyle = beerStyle;
		} else {
			// Throw an exception, we don't have that beerStyle available.
			throw new IllegalArgumentException("Available beer styles are " + beerStyles.keySet().toString() + ".  You must choose from the available beer styles.");
		}
	}

	/*
	Getters / Accessors.
	*/

	/**
	 * Get the style of this beer.
	 * 
	 * @return a string containing the style of this beer.
	 */
	public String getBeerStyle() {
		return this.beerStyle;
	}

	/**
	 * Get the ABV of this beer.
	 * 
	 * @return a Double containing the ABV of this beer.
	 */
	public Double getBeerABV() {
		return beerStyles.get(this.getBeerStyle());
	}

	/**
	 * Get the number of beers served.
	 * @return an int.
	 */
	public static int getBeersServed() {
		return beersServed;
	}

	/**
	 * Get a HashMap of the available beer styles.
	 * @return that List.
	 */
	public static Map getBeerStyles() {
		return beerStyles;
	}

	/*
	Override toString to print out our beer with.
	*/

	@Override
	public String toString() {
		return String.format("container => %s\ntemperature => %s\nonTheRocks => %s\nbeerStyle => %s\nbeerABV => %.1f\n", super.getContainer(), super.getTemperature(), super.getOnTheRocks(), this.getBeerStyle(), this.getBeerABV());
	}
	
}
