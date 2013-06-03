package sorting.algorithms;


public class BruteSorter implements Sorter{
	
	public int[] sortIt(int[] a){							//no visibilty without 'public'
		//brute sorting, new sorted array generated
		int[] b = new int[a.length];
		//maybe initialize b with zeros later , no need !!!
		boolean added=false;
		b[0]=a[0];
		for (int i=1;i<a.length;i++){
			added=false;
			for (int j=0; (j<b.length && !added);j++){
				if (a[i]<=b[j]) {
					for (int k=b.length-1;k>j;k--) b[k]=b[k-1]; 
								
					b[j]=a[i];
					added=true;
					}
			}	
			if (!added) {
				b[i]=a[i];
				added=true;
			}
			}
		return b;
	}
}
