package sorting.algorithms;

public class SelectionSorter implements Sorter {

	public int[] sortIt(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int k = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[k]) {
					k = j;
				}
			}
			// swap
			int aux = a[i];
			a[i] = a[k];
			a[k] = aux;
		}
		return a;
	}

}
