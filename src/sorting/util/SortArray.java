package sorting.util;

import sorting.algorithms.*;

public class SortArray {

	public static void main(int[] x) {
		// TODO Auto-generated method stub
		boolean ok;
		int option;
		do {
			ok = false;
			System.out.println("----------------------------------");
			System.out.print("ORIGINAL: ");
			HandleArray.screenIt(x);
			System.out.print("SORTING METHOD: ");
			System.out
					.println("(1)BRUTE  (2)BUBBLE  (3)INSERTION  (4)SELECTION ");
			System.out.print("SELECT: ");
			option = GetNumberInput.getIt();
			if ((option > 0) && (option < 5)) {
				ok = true;
			}
		} while (!ok);

		// option is valid
		Sorter srt;
		switch (option) {
		case 1: {
			System.out.print("BRUTE: ");
			srt = new BruteSorter();
			break;
		}
		case 2: {
			System.out.print("BUBBLE: ");
			srt = new BubbleSorter();
			break;
		}
		case 3: {
			System.out.print("INSERTION: ");
			srt = new InsertionSorter();
			break;
		}
		case 4: {
			System.out.print("SELECTION: ");
			srt = new SelectionSorter();
			break;
		}
		default: { // useless
			srt = new BruteSorter(); // useless
			break; // useless
		} // useless
		}

		HandleArray.screenIt(srt.sortIt(x));
	}
}
