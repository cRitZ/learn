package sorting.util;

import java.util.Scanner;

public class GetNumberInput {

	// input a string,
	// return an integer
	// turn 0, otherwise
	public static int getIt() {
		Scanner in = new Scanner(System.in);
		int n = 0;
		String input;
		input = in.nextLine().trim();
		// check if valid integer given
		if (ValidateInput.isGoodNumber(input)) {
			n = Integer.parseInt(input);
		}

		// in.close();
		return n;
	}
}
