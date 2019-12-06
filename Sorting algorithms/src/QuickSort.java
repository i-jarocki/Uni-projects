

public class QuickSort {
	private int [] tab;
	private long time_before;
	private long time_after;

	
	public QuickSort(Tablica tab) {
		this.tab = new int [tab.length()];
		for (int i=0; i<tab.length(); i++) {
			this.tab[i]= tab.getValue(i);
		}
	}
	
	public void print() {
		for (int i=0; i<tab.length; i++) {
			if (i%20==0) {
				System.out.print("\n");
			}
			System.out.printf("%5s", tab[i]);
		}
	}
	
	private void swap(int i, int j) {
		int x=tab[i];
		tab[i]=tab[j];
		tab[j]=x;
	}
	
	private void quicksort(int p, int k) {
		int po=p, ko=k, pivot=tab[p+(k-p)/2];
		while(po<=ko) {
			while(tab[po]<pivot) {
				po++;
			}
			while(pivot<tab[ko]) {
				ko--;
			}
			if(po<=ko) {
				swap(po, ko);
				po++;
				ko--;
			}
		}
		if (p<ko) {
			quicksort(p, ko);
		}
		if (po<k) {
			quicksort(po, k);
		}
	}
	
	public void sort() {
		time_before=System.currentTimeMillis();
		quicksort(0, tab.length-1);
	    time_after=System.currentTimeMillis();
	    System.out.printf("%5s%10d \n","Czas sortowania QuickSort wyniós³: " , (time_after-time_before));
	}
	
	
	
	
	
}	
