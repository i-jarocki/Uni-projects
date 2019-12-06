import java.io.File;
import java.util.Scanner;


public class Tree_main {
	
	public static void main(String[] args) {
		BSTnode root = new BSTnode("bazyliszek", null);
		node root2 = new node("bazyliszek", null);
		BSTtree bst = new BSTtree(root);
		tree tree = new tree(root2);
		File file1 = new File("plik.txt");
		File file2 = new File("plik2.txt");
		Scanner scan = new Scanner(System.in);
		String word;
		
		System.out.println("Wybierz z kt�rego pliku zczyta� dane (1-kr�tki, else-d�ugi): ");
		int dane = scan.nextInt();
		if (dane==1) {
			bst.BSTload(file1);
			tree.load(file1);
		}
		else {
			bst.BSTload(file2);
			tree.load(file2);
		}
		int i=0;
		while(i!=9) {
			System.out.println("\n\nWybierz co chcesz zrobi�: \n0) Dodaj s�owo.\n1) Usu� s�owo.\n2) Sprawdz czy s�owo znajduje si� w drzewie. \n3) Wypisz inorder.\n4) Wypisz preorder.\n5) Wypisz postorder.\n6) Wypisz top-down.\n7) Wypisz bottom-up.\n8) Wypisz drzewo warstwowo. \n9) Zako�cz program.\n");
			i = scan.nextInt();
			switch(i) {
			case(0):{
				System.out.println("Podaj s�owo do dodania: ");
				word = scan.next();
				bst.add(word);
				break;
			}
			case(1):{
				System.out.println("Podaj s�owo do usuni�cia: ");
				word = scan.next();
				bst.delete(word);
				break;
			}
			case(2):{
				System.out.println("Podaj s�owo do wyszukania: ");
				word = scan.next();
				System.out.println(bst.search(word));
				break;
			}
			case(3):{
				System.out.println("Drzewo w porz�dku inorder: ");
				bst.BSTinorder(root);
				break;
			}
			case(4):{
				System.out.println("Drzewo w porz�dku preorder: ");
				bst.BSTpreorder(root);
				break;
			}
			case(5):{
				System.out.println("Drzewo w porz�dku postorder: ");
				bst.BSTpostorder(root);
				break;
			}
			case(6):{
				System.out.println("Drzewo w widoku top-down: ");
				bst.BSTtop_down();
				break;
			}
			case(7):{
				System.out.println("Drzewo w widoku bottom-up: ");
				bst.BSTbottom_up();
				break;
			}
			case(8):{
				System.out.println("Drzewo w widoku warstwami: ");
				bst.BSTstructure();
				break;
			}
			case(9):{
				break;
			}
			default:{
				System.out.println("Podano z�� liczb� spr�buj jeszcze raz.");
			}
			}
		}
		scan.close();
	}
}
