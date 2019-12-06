import java.util.Scanner;

public class Leks_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Leks_Tree tree = new Leks_Tree();
		tree.load();
		int i=0;
		String slowo;
		Scanner scan = new Scanner(System.in);
		while (i!=7) {
			System.out.println("\n\nPodaj co chcesz zrobi�:\n0) Wypisz ilo�� s��w.\n1) Wypisz ilo�� w�z��w. \n2) Wypisz rozmiar pliku. \n3) Sprawdz czy s�owo wyst�puje. \n4) Podaj najdluzszy prefiks dla zadanej dlugosci. \n5) Podaj najdluzszy prefiks dla zadanej lub wiekszej dlugosci. \n6) Wypisz slowa wywodzace sie od zadanego prefiksu. \n7) Zakoncz program.\n");
			i = scan.nextInt();
			switch(i) {
			case(0):{
				System.out.print("\nPlik posiada " + tree.getSlowa() + " s��w.");
				break;
			}
			case(1):{
				System.out.print("\nPlik posiada " + tree.getNodes() + " w�z��w.");
				break;
			}
			case(2):{
				System.out.print("\nPlik ma rozmiar " + tree.getFileSize() + " KB");
				break;
			}
			case(3):{
				System.out.print("\nPodaj s�owo do sprawdzenia: ");
				slowo=scan.next();
				System.out.print("Czy s�owo wyst�puje? " + tree.search(slowo));
				
				break;
			}
			case(4):{
				System.out.print("\nPodaj zadan� d�ugo��. ");
				int n= scan.nextInt();
				tree.most_pre(n);
				break;
			}
			case(5):{
				System.out.print("\nPodaj zadan� d�ugo��. ");
				int n= scan.nextInt();
				tree.most_pre2(n, tree.getRoot());
				break;
			}
			case(6):{
				System.out.print("\nPodaj prefiks do sprawdzenia: ");
				slowo=scan.next();
				tree.words_from_pre(slowo);
				break;
			}
			case(7):{
				break;
			}
			default:{
				System.out.println("\nPodano z�� liczb� spr�buj jeszcze raz.");
			}
			
			}
		}
		scan.close();
	}
	

}
