/*
 * IT ### Final Project.
 * MiscTest tests class Misc.
 * Jeffrey Cutter
 */
package sillygame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class MiscTest is to test Class Misc.
 * @author jcutter
 */
public class MiscTest {

	/**
	 * Main method.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		// Test yesOrNo.
		System.out.printf("Result: %s\n", Misc.yesOrNo("\nTry answering something besides y or n, then answer y, result should be true."));
		System.out.printf("Result: %s\n", Misc.yesOrNo("\nAnswer n, result should be false."));

		// Test getIntAnswer.
		System.out.printf("Result: %d\n", Misc.getIntAnswer("\nTry answering 0, should reject, then try answering 1.  Result should be 1.", 1, 2));
		System.out.printf("Result: %d\n", Misc.getIntAnswer("\nTry answering 3, should reject, then try answering 2.  Result should be 2.", 1, 2));

		// Test printCountedList.
		List<String> myList = new ArrayList(Arrays.asList("one", "two", "three", "four", "five"));
		Misc.printCountedList(myList);	

	}
	
}
