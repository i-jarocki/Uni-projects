import java.util.Random;

public class Tablica {
	private int [] tab;
	
	public Tablica(int index, int zakres) {
		tab = new int[index]; 
		for (int i=0; i<index; i++) {
			tab[i]=losuj(zakres);
		}
		
	}
	
	public Tablica(int index) {
		tab = new int[index]; 
		int ind=index/10;
		int k=-1;
		for (int i=0; i<index; i++) {
			if (i%ind==0) {
				k++;
			}
			tab[i]=k;
		}
	}
	
	public Tablica(int index, String s) {
		tab = new int[index]; 
		int ind=index/10;
		int k=10;
		for (int i=0; i<index; i++) {
			if (i%ind==0) {
				k--;
			}
			tab[i]=k;
		}
	}
	
	private int losuj(int zakres) {
		Random rand = new Random();
		return rand.nextInt(zakres);
	}
	
	public int getValue(int i) {
		return tab[i];
	}
	
	public int length() {
		return tab.length;
	}
}	
