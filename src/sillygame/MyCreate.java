/*
 * IT ### Final Project
 * MyCreate Class
 * Jeffrey Cutter
 */
package sillygame;

import java.util.regex.Pattern;

/**
 * MyCreate class creates a clone for life in the silly game.
 *
 * @author jcutter
 */
public class MyCreate {

	/*
	 Class variables - private to protect them.
	 Belong to the class, not to instances.
	*/

	/**
	 * A class variable int to track how many clones and provide a clone number to each clone.
	 */
	private static int numberOfClones = 0;

	/*
	 Instance variables - private to protect them (encapsulation).
	 */

	/**
	 * The clones first name.
	 */
	private String firstName;

	/**
	 * The clones last name.
	 */
	private String lastName;

	/**
	 * The clones age in years. Must be between 0 and 120.
	 */
	private int age;

	/**
	 * The clones gender. Must be one of male, female, or unknown.
	 */
	private String gender;

	/**
	 * Does the clone like beer? true is yes, false is no.
	 */
	private boolean likesBeer;

	/**
	 * An id number for our clone.
	 */
	private final int cloneNumber;  // final because we don't want it to change.

	/**
	 * A boolean to track if clone has been introduced already or not.
	 */
	private boolean introduced = false;


	/*
	 Constructors.
	 */

	/**
	 * MyCreate constructor allowing for setting firstName and lastName.
	 *
	 * @param firstName is a String containing the first name of the clone.
	 * @param lastName is a String containing the last name of the clone.
	 * @throws IllegalArgumentException if any parameters do not pass
	 * validation.
	 */
	public MyCreate(String firstName, String lastName) throws IllegalArgumentException {

		// Call setters to do the work of validating the input and then initializing.
		// Setters will throw if parameters do not pass validation.
		setFirstName(firstName);
		setLastName(lastName);
		this.age = -1;
		this.gender = "unknown";
		this.likesBeer = false; // Assume does not like beer until told otherwise.
		// Increment numberOfClones and assign cloneNumber.
		numberOfClones++;
		this.cloneNumber = numberOfClones;

	}

	/**
	 * MyCreate constructor allowing for setting all instance variables.
	 *
	 * @param firstName is a String containing the first name of the clone.
	 * @param lastName is a String containing the last name of the clone.
	 * @param age is an int containing the age of the clone.
	 * @param gender is an String containing the gender of the clone
	 * (male|female|unknown).
	 * @param likesBeer is a boolean specifying whether the clone likes beer
	 * (true) or not (false).
	 * @throws IllegalArgumentException if any parameters do not pass
	 * validation.
	 */
	public MyCreate(String firstName, String lastName, int age, String gender, boolean likesBeer) throws IllegalArgumentException {
		// Call setters to do the work of validating the input and then initializing.
		// Setters will throw if parameters do not pass validation.
		setFirstName(firstName);
		setLastName(lastName);
		setAge(age);
		setGender(gender);
		setLikesBeer(likesBeer);
		// Increment numberOfClones and assign cloneNumber.
		numberOfClones++;
		this.cloneNumber = numberOfClones;
	}

	/*
	 Public Methods.
	 */

	/**
	 * Prints an introduction statement for MyCreate.
	 * @return a boolean true if performed introduction, false if introduction was previously performed.
	 */
	public boolean introduction() {
		// boolean to return if we do an introduction.
		boolean performedIntro = false;

		// If clone has not already been introduced, do the introduction.
		if ( introduced  != true ) {
			// If clone likes beer.
			if (this.likesBeer == true) {
				// Offer home brew beer.
				if ( this.age >= 21 ) {
					System.out.printf("Hello %s %s!  You'll definitely want to try one of my delicious home brewed beers!\n", this.getFirstName(), this.getLastName());
				} else {
					System.out.printf("Hello %s %s!  It's a good thing for you there is no minimum age for drinking beer in this Silly Game!\n", this.getFirstName(), this.getLastName());
				}
			} else {
				// Offer home brew tea.
				if ( this.age >= 21 ) {
					System.out.printf("Hello %s %s!  You might want to try one of my home brewed beers.  If not, please be sure to try some of my delicious refrigerator brewed iced tea!\n", this.getFirstName(), this.getLastName());
				} else {
					System.out.printf("Hello %s %s!  Please be sure to try some of my delicious refrigerator brewed iced tea!\n", this.getFirstName(), this.getLastName());
	
				}
			}
			// Mark this clone as introduced.
			this.introduced = true;
			
			// Update return that we did perform the introduction.
			performedIntro = true;

		}
		return performedIntro;
	}

	/**
	 * Say goodbye to this MyCreate.
	 */
	public void sayGoodbye() {
		System.out.printf("Goodbye %s %s.\n", this.getFirstName(), this.getLastName());
	}

	/*
	 Setters / Mutators.  Final so we can safely call them in the constructors
	 without fear of subclasses overriding them.
	 */

	/**
	 * Set the first name of the clone.
	 *
	 * @param firstName is a String containing the first name of the clone.
	 * @throws IllegalArgumentException if firstName doesn't pass
	 * validation.
	 */
	public final void setFirstName(String firstName) throws IllegalArgumentException {
		validateName(firstName); // Throws if not a name.
		this.firstName = firstName;
	}

	/**
	 * Set the last name of the clone.
	 *
	 * @param lastName is a String containing the last name of the clone.
	 * @throws IllegalArgumentException if lastName doesn't pass validation.
	 */
	public final void setLastName(String lastName) throws IllegalArgumentException {
		validateName(lastName); // Throws if not a name.
		this.lastName = lastName;
	}

	/**
	 * Set the age of the clone.
	 *
	 * @param age is an int containing the age of the clone in years.
	 * @throws IllegalArgumentException if age doesn't pass validation.
	 */
	public final void setAge(int age) throws IllegalArgumentException {
		validateAge(age); // Throws if age isn't valid.
		this.age = age;
	}

	/**
	 * Set the boolean likesBeer.
	 *
	 * @param likesBeer is a boolean to hold if the clone likes beer or not.
	 */
	public final void setLikesBeer(boolean likesBeer) {
		// No validation here, the boolean has to be true or false to
		// get through the constructor.
		this.likesBeer = likesBeer;
	}

	/**
	 * Set the gender of the clone.
	 *
	 * @param gender is an String (male|female|unknown)
	 * @throws IllegalArgumentException if age doesn't pass validation.
	 */
	public final void setGender(String gender) throws IllegalArgumentException {
		validateGender(gender); // Throws if gender isn't valid.
		this.gender = gender;
	}

	/*
	 Getters / Accessors.
	 */

	/**
	 * Get the first name of the clone.
	 *
	 * @return a String of the first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Get the last name of the clone.
	 *
	 * @return a String of the last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Get the age of the clone.
	 *
	 * @return an int of the clone's age.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Get the gender of the clone.
	 *
	 * @return an String of the clone's gender.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Check if the clone likes beer.
	 *
	 * @return a boolean of if the clone likes beer (true=yes, false=no).
	 */
	public boolean getLikesBeer() {
		return likesBeer;
	}

	/**
	 * Get the clone's id number.
	 * 
	 * @return an int of the cloneNumber.
	 */
	public int getCloneNumber() {
		return cloneNumber;
	}

	/**
	 * Get the number of clones that have been instantiated.
	 * 
	 * @return an int of the number of clones that have been instantiated.
	 */
	// Static because this method can't be run against an individual object.
	public static int getNumberOfClones() {
		return numberOfClones;
	}
	
	/**
	 * Returns a one line summary of this MyCreate.
	 * @return a String containing a one line summary of this MyCreate.
	 */
	public String list() {
		String beer = "";
		if ( likesBeer == true ) {
			beer = "likes beer";
		} else {
			beer = "doesn't like beer";
		}
		return String.format("%s %s, age %d, %s, %s.", firstName, lastName, age, gender, beer);
	}

	/*
	 Override toString to print out our MyCreate with.
	 */

	/**
	 * Returns a key => value listing of the MyCreate properties.
	 *
	 * @return a string.
	 */
	@Override
	public String toString() {
		return String.format("id => %d\nfirstName => %s\nlastName => %s\nage => %d\ngender => %s\nlikesBeer => %s\n", cloneNumber, firstName, lastName, age, gender, likesBeer);
	}

	/*
	 Validation methods.
	 */

	/**
	 * Validate a string looks reasonably like a name.
	 *
	 * @param name
	 * @throws IllegalArgumentException if string doesn't pass validation.
	 */
	private void validateName(String name) throws IllegalArgumentException {

		// Validate parameter name appears to be a name by checking that it
		// matches one of the following:
		// One or more letters.
		// [A-Za-z]+
		//
		// 1 or more letters, an apostrophy, and 1 or more letters.
		// [A-Za-z]+'[A-Za-z]+
		//
		// 1 or more letters, a hyphen, and 1 or more letters.
		// [A-Za-z]+-[A-Za-z]+
		boolean goodName = Pattern.matches("[A-Za-z]+|[A-Za-z]+'[A-Za-z]+|[A-Za-z]+-[A-Za-z]+", name);

		if (!goodName) {
			throw new IllegalArgumentException("Names must contain at least 1 letter and only contain letters, an apostrophe between letters, and/or a hyphen between letters.");
		}
	}

	/**
	 * Validate that param int is a valid age (between 0 and 120).
	 *
	 * @param age is an int
	 * @throws IllegalArgumentException if age is not between 0 and 120.
	 */
	private void validateAge(int age) throws IllegalArgumentException {
		// If age is less than 0 or greater than 120, throw exception.
		if (age < 0 || age > 120) {
			throw new IllegalArgumentException("Ages must be between 0 and 120.");
		}
	}

	/**
	 * Validate that gender is one of [male|female|unknown].
	 *
	 * @param gender is a String.
	 * @throws IllegalArgumentException if gender isn't one of male, female,
	 * or unknown.
	 */
	private void validateGender(String gender) throws IllegalArgumentException {
		// if gender doesn't equal male or female or unknown, then throw.
		if (!(gender.equals("male") || gender.equals("female") || gender.equals("unknown"))) {
			throw new IllegalArgumentException("Genders must be either male, female, or unknown.");
		}
	}

}
