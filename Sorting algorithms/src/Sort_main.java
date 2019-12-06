
public class Sort_main {
	
	private static void kolejne(int i) {
		Tablica tab= new Tablica(i, 10);
		BubbleSort bubble = new BubbleSort(tab);
		ShellSort shell = new ShellSort(tab);
		InsertSort insert = new InsertSort(tab);
		SelectSort select = new SelectSort(tab);
		CollSort coll = new CollSort(tab);
		QuickSort quick = new QuickSort(tab);
		System.out.println("\nDla "+i+" elementów: ");
		bubble.sort();
		shell.sort();
		insert.sort();
		select.sort();
		coll.sort();
		quick.sort();
	}
	
	private static void kolejne_upo(int i) {
		Tablica tab= new Tablica(i);
		BubbleSort bubble = new BubbleSort(tab);
		ShellSort shell = new ShellSort(tab);
		InsertSort insert = new InsertSort(tab);
		SelectSort select = new SelectSort(tab);
		CollSort coll = new CollSort(tab);
		QuickSort quick = new QuickSort(tab);
		System.out.println("\nDla "+i+" rosn¹co posortowanych uporz¹dkowanych elementów: ");
		bubble.sort();
		shell.sort();
		insert.sort();
		select.sort();
		coll.sort();
		quick.sort();
	}
	
	private static void kolejne_nupo(int i) {
		String s="";
		Tablica tab= new Tablica(i, s);
		BubbleSort bubble = new BubbleSort(tab);
		ShellSort shell = new ShellSort(tab);
		InsertSort insert = new InsertSort(tab);
		SelectSort select = new SelectSort(tab);
		CollSort coll = new CollSort(tab);
		QuickSort quick = new QuickSort(tab);
		System.out.println("\nDla "+i+" malej¹co posortowanych elementów: ");
		bubble.sort();
		shell.sort();
		insert.sort();
		select.sort();
		coll.sort();
		quick.sort();
	}
	
	public static void main(String[] args) {
		///////////////////////////RANDOMOWE
		//kolejne(1000);
		kolejne(10000);
	    //kolejne(100000);
		//kolejne(1000000);
		
		//////////////////////////POSORTOWANE(ROSN¥CE)
		//kolejne_upo(1000);
		kolejne_upo(10000);
		//kolejne_upo(100000);
		//kolejne_upo(1000000);
		
		///////////////////////////POSORTOWANE(MALEJ¥CE)
		//kolejne_nupo(1000);
		kolejne_nupo(10000);
		//kolejne_nupo(100000);
		//kolejne_nupo(1000000);
	}
}
