/*
 * IT ### Final Project.
 * Subclass of additional class Beverage.
 * Because I wanted to try inheritence.
 */
package sillygame;

import java.util.ArrayList;
import java.util.List;

/**
 * Tea Class, extends Beverage class.
 * @author jcutter
 */
public class Tea extends Beverage {

	/*
	Class variables - shared by the class, not specific to a particular object.
	Private to protect them.
	*/

	/**
	 * List of Tea types available.
	 * Validation of teaType will be performed using these.
	 */
	// Static because it's common to all objects of the class.
	private static final List<String> teaTypes = new ArrayList<>(5);
	// A static initializer to load our class teaTypes hashMap.
	static {
		teaTypes.add("Orange Pekoe");
		teaTypes.add("Mango Ceylon");
		teaTypes.add("Earl Grey");
		teaTypes.add("Moroccan Mint");
		teaTypes.add("English Breakfast");
	}

	/**
	 * Tracks the number of teas served.
	 */
	private static int teasServed;

	/*
	Instance variables.
	*/

	/**
	 * Tea Type.  Restricted to one of keys of HashMap teaTypes.
	 */
	private String teaType;

	/*
	Constructors.
	*/

	/**
	 * No parameters Tea constructor.  Sets defaults.
	 * 
	 * @throws IllegalArgumentException  if received from parameterized constructor.
	 */
	public Tea() throws IllegalArgumentException {
		// Call the no parameters constructor in the Beverage class.
		super();
		// Set teaType.
		setTeaType("English Breakfast");
	}

	/**
	 * Tea constructor accepting the required parameters.
	 * 
	 * @param container is a String - From Beverage and validated there.
	 * @param temperature is a string - From Beverage and validated there.
	 * @param onTheRocks is a boolean - From Beverage.
	 * @param teaType is a String with valid values specified in teaTypes keys.
	 * @throws IllegalArgumentException  if validation fails in the setters.
	 */
	public Tea(String container, String temperature, boolean onTheRocks, String teaType) throws IllegalArgumentException {
		// Call the constructor in the Beverage class.
		super(container, temperature, onTheRocks);
		// Set teaType.
		setTeaType(teaType);
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
		// Serve our beverage by printing it out.
		System.out.printf("Here is your %s tea in a %s, served nice and %s", this.getTeaType(), super.getContainer(), super.getTemperature());
		// If on the rocks.
		if (this.getOnTheRocks() == true) {
			// Say so.
			System.out.println(" on the rocks.");
		} else {
			// Just end the sentence.
			System.out.println(".");
		}

		// Increment number of beverages served.
		teasServed++;
		// This is in super, but is only protected, not private, so we can do this here.
		beveragesServed++;

	}

	/*
	Setters / Mutators.
	*/

	/**
	 * Set the teaType
	 * 
	 * @param teaType is a String.
	 * @throws IllegalArgumentException if teaType choice is not one of the keys in (available) teaTypes.
	 */
	public final void setTeaType(String teaType) throws IllegalArgumentException {
		// If the teaType choice is one of the available teaTypes.
		if (teaTypes.contains(teaType)) {
			// Use it.
			this.teaType = teaType;
		} else {
			// Throw an exception, we don't have that teaType available.
			throw new IllegalArgumentException("Available tea types are " + teaTypes + ".  You must choose from the available tea types.");
		}
	}

	/*
	Getters / Accessors.
	*/

	/**
	 * Get the type of this tea.
	 * 
	 * @return a string containing the type of this tea.
	 */
	public String getTeaType() {
		return this.teaType;
	}

	/**
	 * Get the number of teas served.
	 * @return an int.
	 */
	public static int getTeasServed() {
		return teasServed;
	}
	
	/**
	 * Get the List of teaTypes.
	 * @return the List.
	 */
	public static List getTeaTypes() {
		return teaTypes;	
	}

	
	/*
	Override toString to print out our tea with.
	*/

	@Override
	public String toString() {
		return String.format("container => %s\ntemperature => %s\nonTheRocks => %s\nteaType => %s\n", super.getContainer(), super.getTemperature(), super.getOnTheRocks(), this.getTeaType());
	}

}
