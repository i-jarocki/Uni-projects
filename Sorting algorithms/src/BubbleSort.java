
public class BubbleSort {
	private int [] tab;
	private long time_before;
	private long time_after;
	
	public BubbleSort(Tablica tab) {
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
	
	public void sort() {
		time_before=System.currentTimeMillis();
	    for(int i=0; i<tab.length; i++){
	        for (int j=0; j<tab.length-1; j++){
	            if (tab[j]>tab[j+1]){
	                int x=tab[j];
	                tab[j]=tab[j+1];
	                tab[j+1]=x;
	            }
	        }
	    }
	    time_after=System.currentTimeMillis();
	    System.out.printf("%5s%9d \n","Czas sortowania BubbleSort wyniós³: " , (time_after-time_before));
	}
	
	
	
	
	
}	
