
public class InsertSort {
	private int [] tab;
	private long time_before;
	private long time_after;
	
	public InsertSort(Tablica tab) {
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
		time_before=System.currentTimeMillis();
	    for(int i=0; i<tab.length; i++){
	        for (int j=i-1; -1<j; j--){
	            if (tab[j]>tab[j+1]){
	                swap(j,j+1);
	            }
	            else j=-1;
	        }
	    }
	    time_after=System.currentTimeMillis();
	   
	    System.out.printf("%5s%9d \n","Czas sortowania InsertSort wyniós³: " , (time_after-time_before));
	}
	
	
	
	
	
}	
