/*
 * IT ### TeaTest class to test Tea class.
 * Jeffrey Cutter
 */
package sillygame;

/**
 * TeaTest is used to test the Tea class.
 * @author jcutter
 */
public class TeaTest {
	
	/**
	 * main method
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		// Create a Tea using the no param constructor.
		Tea tea1 = new Tea();

		// Print out the object using the toString from Tea.
		System.out.printf("\n%s\n", tea1);

		// Test setters.
		tea1.setContainer("mug");
		tea1.setTemperature("hot");
		tea1.setOnTheRocks(false);
		tea1.setTeaType("Orange Pekoe");

		// Print out the object using the toString from Tea.
		System.out.printf("%s\n", tea1);

		// Test introduction.
		tea1.serveBeverage();
		System.out.println();


		// Create a Tea using the other constructor.
		Tea tea2 = new Tea("glass", "cold", true, "Mango Ceylon");

		// Test getters.

		System.out.printf("getContainer => %s\n", tea2.getContainer());
		System.out.printf("getTemperature => %s\n", tea2.getTemperature());
		System.out.printf("getOnTheRocks => %s\n", tea2.getOnTheRocks());
		System.out.printf("teaType => %s\n", tea2.getTeaType());
		System.out.println();

		// Test introduction for non-tea drinker.
		tea2.serveBeverage();
		System.out.println();
		
		// Test and catch invalid parameter checking.

		// Test constructor with invalid container.
		try {
			Tea tea3 = new Tea("wine glass", "cold", false, "English Breakfast");
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		// Test constructor with invalid temperature.
		try {
			Tea tea3 = new Tea("glass", "ice cold", true, "English Breakfast");
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		// Test constructor with invalid tea type.
		try {
			Tea tea3 = new Tea("glass", "cold", true, "Lipton");
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		// Test getTeaTypes.
		System.out.println();
		System.out.println(Tea.getTeaTypes());

		// Test getTeasServed.
		System.out.printf("\n%d teas were served.\n", Tea.getTeasServed());
		// Test getBeveragesServed.
		System.out.printf("\n%d total beverages were served.\n", Tea.getBeveragesServed());


	}

}
