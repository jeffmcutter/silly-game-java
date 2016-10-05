/*
 * IT ###  BeverageTest class to test Beverage.
 * Jeffrey Cutter
 */
package sillygame;

/**
 * BeverageTest is used to test Beverage class.
 * @author jcutter
 */
public class BeverageTest {

	/**
	 * main method
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		// Create a Beverage using the no param constructor.
		Beverage beverage1 = new Beverage();

		// Print out the object using the toString from Beverage.
		System.out.printf("\n%s\n", beverage1);

		// Test setters.
		beverage1.setContainer("mug");
		beverage1.setTemperature("hot");
		beverage1.setOnTheRocks(false);

		// Print out the object using the toString from Beverage.
		System.out.printf("%s\n", beverage1);

		// Test introduction.
		beverage1.serveBeverage();
		System.out.println();


		// Create a Beverage using the other constructor.
		Beverage beverage2 = new Beverage("glass", "cold", true);

		// Test getters.

		System.out.printf("getContainer => %s\n", beverage2.getContainer());
		System.out.printf("getTemperature => %s\n", beverage2.getTemperature());
		System.out.printf("getOnTheRocks => %s\n", beverage2.getOnTheRocks());
		System.out.println();

		// Test introduction for non-beer drinker.
		beverage2.serveBeverage();
		System.out.println();
		
		// Test and catch invalid parameter checking.

		// Test constructor with invalid container.
		try {
			Beverage beverage3 = new Beverage("wine glass", "cold", false);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		// Test constructor with invalid temperature.
		try {
			Beverage beverage3 = new Beverage("glass", "ice cold", true);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		System.out.println();

		// Test getValidTemperatures.
		System.out.println(Beverage.getValidTemperatures());

		// Test getValidContainers.
		System.out.println(Beverage.getValidContainers());

		// Test getBeveragesServed.
		System.out.printf("\n%d beverages were served.\n", Beverage.getBeveragesServed());

	}

}
