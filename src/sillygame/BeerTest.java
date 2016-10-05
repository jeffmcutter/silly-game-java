/*
 * IT ###  BeerTest class to test Beer class.
 * Jeffrey Cutter
 */
package sillygame;

/**
 * BeerTest is used to test the Beer class.
 * @author jcutter
 */
public class BeerTest {
	
	/**
	 * main method
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		// Create a Beer using the no param constructor.
		Beer beer1 = new Beer();

		// Print out the object using the toString from Beer.
		System.out.printf("\n%s\n", beer1);

		// Test setters.
		beer1.setContainer("mug");
		beer1.setTemperature("hot");
		beer1.setOnTheRocks(false);
		beer1.setBeerStyle("English Bitter");

		// Print out the object using the toString from Beer.
		System.out.printf("%s\n", beer1);

		// Test getting beer style and abv.
		System.out.printf("The ABV for %s is %.1f.\n\n", beer1.getBeerStyle(), beer1.getBeerABV());


		// Create a Beer using the other constructor.
		Beer beer2 = new Beer("glass", "cold", true, "Barley Wine");

		// Test getters.

		System.out.printf("getContainer => %s\n", beer2.getContainer());
		System.out.printf("getTemperature => %s\n", beer2.getTemperature());
		System.out.printf("getOnTheRocks => %s\n", beer2.getOnTheRocks());
		System.out.printf("getBeerStyle => %s\n", beer2.getBeerStyle());
		System.out.printf("getBeerABV => %s\n", beer2.getBeerABV());
		System.out.println();

		// Test and catch invalid parameter checking.

		// Test constructor with invalid container.
		try {
			Beer beer3 = new Beer("wine glass", "cold", false, "American Lager");
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		// Test constructor with invalid temperature.
		try {
			Beer beer3 = new Beer("glass", "ice cold", true, "American Lager");
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		// Test constructor with invalid beer style.
		try {
			Beer beer3 = new Beer("glass", "cold", true, "Bud Light");
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}

		// Test getBeerStyles.
		System.out.println();
		System.out.println(Beer.getBeerStyles());
		System.out.println();

		// A couple more test cases for different serving styles.
		Beer beer3 = new Beer("mug", "cold", false, "American Lager");
		Beer beer4 = new Beer("glass", "warm", false, "English Bitter");

		// Test serving beverages.
		beer1.serveBeverage();
		beer2.serveBeverage();
		beer3.serveBeverage();
		beer4.serveBeverage();
		

		// Test getBeersServed.
		System.out.printf("\n%d beers were served.\n", Beer.getBeersServed());
		// Test getBeveragesServed.
		System.out.printf("%d total beverages were served.\n", Beer.getBeveragesServed());

	}

}
