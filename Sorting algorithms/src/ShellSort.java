import java.util.ArrayList;

public class ShellSort {
	private int [] tab;
	private long time_before;
	private long time_after;
	private ArrayList<Integer> podz= new ArrayList<Integer>(0);
	
	public ShellSort(Tablica tab) {
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
	
	public void printpodz() {
		generuj();
		for (int i=0; i<podz.size(); i++) {
			if (i%20==0) {
				System.out.print("\n");
			}
			System.out.printf("%5s", podz.get(i));
		}
	}
	
	private void generuj() {
		podz.add(1);
		while (podz.get(podz.size()-1)<tab.length/2) {
			podz.add((int)2.25*podz.get(podz.size()-1));
		}
	}
	
	public void sort() {
		time_before=System.currentTimeMillis();
		generuj();
		int i, j;
		int podzial;
		int temp;
		while(0<podz.size()) {
			podzial=podz.get(podz.size()-1);
			for (j=podzial; j<tab.length; j++){
				temp=tab[j];
				i=j;
				while(i>podzial-1 && tab[i-podzial]>=temp) {
					tab[i]=tab[i-podzial];
			         i-=podzial;
				}
				tab[i] =temp;
	    	}
			podz.remove(podz.size()-1);
		}
		 time_after=System.currentTimeMillis();
		 System.out.printf("%5s%10d\n", "Czas sortowania ShellSort wyniós³: " , (time_after-time_before));
	}

}
