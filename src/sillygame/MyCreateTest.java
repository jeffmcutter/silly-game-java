/*
 * IT ### MyCreateTest class to test MyCreate.
 * Jeffrey Cutter
 */

package sillygame;

import static sillygame.MyCreate.getNumberOfClones;

/**
 * MyCreateTest is used to test MyCreate and related classes.
 * @author jcutter
 */
public class MyCreateTest {

	/**
	 * main method
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		// Create a MyCreate using one of the two constructors.
		MyCreate clone1 = new MyCreate("John", "Doe");

		// Print out the object using the toString from MyCreate.
		System.out.printf("\n%s\n", clone1);

		// Test setters not already tested by constructor.
		clone1.setAge(21);
		clone1.setGender("male");
		clone1.setLikesBeer(true);

		// Print out the object using the toString from MyCreate.
		System.out.printf("%s\n", clone1);

		// Create a MyCreate using the other constructor.
		MyCreate clone2 = new MyCreate("Jane", "Doe", 21, "female", false);

		// Test getters.

		System.out.printf("id => %d\n", clone2.getCloneNumber());
		System.out.printf("firstName => %s\n", clone2.getFirstName());
		System.out.printf("lastName => %s\n", clone2.getLastName());
		System.out.printf("age => %d\n", clone2.getAge());
		System.out.printf("gender => %s\n", clone2.getGender());
		System.out.printf("likesBeer => %s\n", clone2.getLikesBeer());

		// Test remaining setters.
		clone2.setFirstName("Janie");
		clone2.setLastName("Smith");

		// Print out the object using the toString from MyCreate.
		System.out.printf("\n%s\n", clone2);

		// Test and catch invalid parameter checking.

		// Test constructor with invalid first name.
		try {
			MyCreate clone3 = new MyCreate("123", "Smith");
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		// Test constructor with invalid last name.
		try {
			MyCreate clone3 = new MyCreate("Jane", "");
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		// Test constructor with invalid age.
		try {
			MyCreate clone3 = new MyCreate("Jane", "Smith", -1, "femail", false);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		// Test constructor with invalid gender.
		try {
			MyCreate clone3 = new MyCreate("Jane", "Smith", 1, "femail", false);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		// It takes 4 clones to test all paths.
		MyCreate clone3 = new MyCreate("Jack", "Smith", 20, "male", true);
		MyCreate clone4 = new MyCreate("Jill", "Smith", 20, "female", false);

		// Test list.

		System.out.println();
		System.out.println(clone1.list());
		System.out.println(clone2.list());
		System.out.println(clone3.list());
		System.out.println(clone4.list());

		// Test introduction.
		System.out.println();
		clone1.introduction();
		clone2.introduction();
		clone3.introduction();
		clone4.introduction();
		

		// Test getNumberOfClones.

		System.out.println();
		System.out.printf("%d clones have been successfully instantiated.\n", getNumberOfClones());
	}

}
