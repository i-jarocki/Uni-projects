import java.util.ArrayList;
import java.util.Collections;

public class CollSort {
	ArrayList<Integer> list = new ArrayList<Integer>(0);
	private long time_before;
	private long time_after;
	
	public CollSort(Tablica tab) {
		for(int i=0; i<tab.length(); i++) {
			list.add(tab.getValue(i));
		}
	}
	
	public void print() {
		for (int i=0; i<list.size(); i++) {
			if (i%20==0) {
				System.out.print("\n");
			}
			System.out.printf("%5s", list.get(i));
		}
	}
	
	public void sort() {
		time_before=System.currentTimeMillis();
		Collections.sort(list);
		time_after=System.currentTimeMillis();
		 System.out.printf("%5s%3d \n","Czas sortowania Collections.Sort wyniós³: " , (time_after-time_before));
	}
}
