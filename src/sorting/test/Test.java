package sorting.test;

import java.util.Scanner;
import sorting.util.*;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in2;
		in2 = new Scanner(System.in);

		if (ValidateInput.isGoodNumber(in2.nextLine())) {
			System.out.println("ok");
		} else {
			System.out.println("not ok");
		}

		in2.close();
	}

}
