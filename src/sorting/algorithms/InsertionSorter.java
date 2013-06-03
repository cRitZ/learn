package sorting.algorithms;

public class InsertionSorter implements Sorter {
	public int[] sortIt(int[] a) {
		// insertion sorting
		for (int i = 1; i < a.length; i++) {
			for (int j = i; (j > 0); j--) {
				// swap
				if (a[j] < a[j - 1]) {
					int aux = a[j];
					a[j] = a[j - 1];
					a[j - 1] = aux;
				}

			}
		}

		return a;
	}
}
