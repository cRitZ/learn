package sorting.algorithms;

public class BubbleSorter implements Sorter {
	public int[] sortIt(int[] a) {
		for (int i = 0; i < a.length; i++) {
			boolean swapped = false;
			for (int j = a.length - 1; j > i; j--) {
				if (a[j] < a[j - 1]) {
					// swap
					int aux = a[j];
					a[j] = a[j - 1];
					a[j - 1] = aux;
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
		return a;
	}

}
