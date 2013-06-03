package sorting;

import sorting.util.*;

public class Main1 {

	public static int[] getData() {
		// get length
		int len=0;
		do{
			System.out.println("ARRAY LENGTH 0<100 : ");
			len=GetNumberInput.getIt();
		}while(len==0);
		
		// get array
		System.out.println(len + " INTEGER VALUE(S) PLEASE:");
		int[] a = HandleArray.getIt(len);
		
		return a;
	}

	public static void main(String[] args) {
		// TODO
		//..added to see merging ?!.

		int[] numbers = getData();

		SortArray.main(numbers);
		
	}

}
