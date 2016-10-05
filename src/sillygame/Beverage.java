/*
 * IT ### Final Project.
 * Additional class of your choice.
 * Jeffrey Cutter
 */
package sillygame;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Beverage Class, addresses beverage needs for MyCreate.
 *
 * @author jcutter
 */
public class Beverage {

	/*
	 Class variables - shared by the class, not specific to a particular object.
	 Private to protect them.
	 */

	/**
	 * Specifies the valid values for container.
	 */
	private static List<String> availableContainers = new ArrayList<>(Arrays.asList("glass", "mug"));

	/**
	 * Specifies the valid values for temperature.
	 */
	private static List<String> availableTemperatures = new ArrayList<>(Arrays.asList("hot", "warm", "cold"));

	/**
	 * An int to track the number of beverages served.
	 */
	// Protected to allow subclasses to update this counter.
	// Not supposed to be as safe as private with getter/setters, but I want to try this too.
	protected static int beveragesServed = 0;

	/*
	 Instance variables - private to protect them (encapsulation).
	 */

	/**
	 * Container. Restricted to one of availableContainers;
	 */
	private String container;

	/**
	 * Serving temperature. Restricted to one of availableTemperatures;
	 */
	private String temperature;

	/**
	 * With ice? true is with ice, false is without ice.
	 */
	private boolean onTheRocks;

	/*
	 Constructors.
	 */

	/**
	 * No parameters Beverage constructor. Sets defaults.
	 *
	 * @throws IllegalArgumentException if received from parameterized
	 * constructor.
	 */
	public Beverage() throws IllegalArgumentException {
		// Call parameterized constructor and pass in default values.
		this("glass", "cold", true);
	}

	/**
	 * Beverage constructor accepting the required parameters.
	 *
	 * @param container is a String with valid values specified in
	 * availableContainers.
	 * @param temperature is a string with valid values specified in
	 * availableTemperatures.
	 * @param onTheRocks is a boolean, true is with ice, false is without
	 * ice.
	 * @throws IllegalArgumentException if validation fails in the setters.
	 */
	public Beverage(String container, String temperature, boolean onTheRocks) throws IllegalArgumentException {
		// Use setters to do the work ov validating the input and then initializing.
		setContainer(container);
		setTemperature(temperature);
		// No need to check validity on boolean.
		this.onTheRocks = onTheRocks;
	}

	/*
	 Public Methods.
	 */

	/**
	 * Serves beverage.
	 */
	public void serveBeverage() {

		// Print out our serving message.
		System.out.printf("Here is your beverage in a %s, served nice and %s", this.getContainer(), this.getTemperature());

		// If on the rocks.
		if (this.getOnTheRocks() == true) {
			// Say so.
			System.out.println(" on the rocks.");
		} else {
			// Just end the sentence.
			System.out.println(".");
		}

		// Increment the number of beverages served.
		beveragesServed++;
	}

	/*
	 Setters / Mutators
	 */

	/**
	 * Set the container type to use for the beverage.
	 *
	 * @param container is a String.
	 * @throws IllegalArgumentException if container is not one of
	 * availableContainers.
	 */
	public final void setContainer(String container) throws IllegalArgumentException {
		// If the container choice is one of the availableContainers.
		if (availableContainers.contains(container)) {
			// Use it.
			this.container = container;
		} else {
			// Throw an exception, we don't have that container type available.
			throw new IllegalArgumentException("Available containers are " + availableContainers + ".  You must choose from the available containers.");
		}
	}

	/**
	 * Set the temperature to use for the beverage.
	 *
	 * @param temperature is a String.
	 * @throws IllegalArgumentException if temperature is not one of
	 * availableTemperatures.
	 */
	public final void setTemperature(String temperature) throws IllegalArgumentException {
		// If the temperature choice is one of the availableTemperatures.
		if (availableTemperatures.contains(temperature)) {
			// Use it.
			this.temperature = temperature;
		} else {
			// Throw an exception, we don't have that temperature available.
			throw new IllegalArgumentException("Available temperature choices are " + availableTemperatures + ".  You must choose from the available temperature choices.");
		}
	}

	/**
	 * Sets whether we want ice in our beverage.
	 * @param onTheRocks is a boolean, true for yes, false for no.
	 */
	public void setOnTheRocks(boolean onTheRocks) {
		this.onTheRocks = onTheRocks;
	}

	/*
	 Getters / Accessors.
	 */

	/**
	 * Get the container for this Beverage.
	 *
	 * @return a string containing the container type.
	 */
	public String getContainer() {
		return this.container;
	}

	/**
	 * Get the temperature of this Beverage.
	 *
	 * @return a string containing the desired temperature.
	 */
	public String getTemperature() {
		return this.temperature;
	}

	/**
	 * Get if beverage is to be served on the rocks.
	 *
	 * @return a boolean of true (yes ice) or false (no ice).
	 */
	public boolean getOnTheRocks() {
		return onTheRocks;
	}

	/**
	 * Get a List of the valid temperatures drinks can be served at.
	 * @return a List of the valid temperatures.
	 */
	public static List getValidTemperatures() {
		return availableTemperatures;
	}

	/**
	 * Get a List of the available container types.
	 * @return a List of available containers.
	 */
	public static List getValidContainers() {
		return availableContainers;
	}

	/**
	 * Get the number of beverages served so far.
	 * @return an int of the number of beverages served so far.
	 */
	public static int getBeveragesServed() {
		return beveragesServed;
	}

	/*
	Override toString to print out our Beverage with.
	*/

	@Override
	public String toString() {
		return String.format("container => %s\ntemperature => %s\nonTheRocks => %s\n", container, temperature, onTheRocks);
	}
}
