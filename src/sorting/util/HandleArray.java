package sorting.util;

import java.util.Scanner;
import sorting.util.*;

public class HandleArray {

	// getting an array of 'len' integers
	// values are input one at a line
	// if not a valid integer value, message(#) , continue.
	public static int[] getIt(int len) {
		Scanner in = new Scanner(System.in);
		int[] x = new int[len];
		int i = 0;
		String input;
		do {
			input = in.nextLine().trim();
			if (ValidateInput.isGoodNumber(input)) {
				x[i] = Integer.parseInt(input);
				i++;
			} else {
				System.out.println("'" + input + "' NOT INTEGER! " + (len - i)
						+ " MORE. CONTINUE."); // (#)
			}
		} while (i < len);
		// in.close();
		return x;
	}

	// display an array
	public static void screenIt(int[] x) {
		for (int i = 0; i < x.length; i++)
			System.out.print(" " + x[i]);
		System.out.println();
	}

}
