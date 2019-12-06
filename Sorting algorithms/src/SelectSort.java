
public class SelectSort {
	private int [] tab;
	private long time_before;
	private long time_after;
	
	public SelectSort(Tablica tab) {
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
	
	public void sort() {
		int min=Integer.MAX_VALUE;
		int min_i=0;
		int max=0;
		int max_i=0;
		time_before=System.currentTimeMillis();
	    for(int i=0; i<tab.length/2; i++){
	        for (int j=i; j<tab.length-i; j++){
	            if (tab[j]<min){
	               min=tab[j];
	               min_i=j;
	            }
	            else if (max<tab[j]) {
	            	max=tab[j];
	            	max_i=j;
	            }
	        }
	        swap(i, min_i);
	        swap(tab.length-i-1, max_i);
	        min=Integer.MAX_VALUE;
	        max=0;
	    }
	    time_after=System.currentTimeMillis();
	    System.out.printf("%5s%9d \n","Czas sortowania SelectSort wyniós³: " , (time_after-time_before));
	}
	
	
	
	
	
}	
