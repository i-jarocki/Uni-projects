import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

class node{
	ArrayList<node> nodes = new ArrayList<node>(0);
	char sign;
	node parent;
	boolean end;
	
	public node() {
		this.parent=null;
		this.sign=0;
		this.end=false;
	}
	
	public node(char sign) {
		this.sign = sign;
	}
	
	public node(char sign, node parent) {
		this.sign=sign;
		this.parent=parent;
	}
	
	public void add(node node) {
		nodes.add(node);
	}
	
	public void add(char sign) {
		nodes.add(new node(sign));
	}
	
	public void add(char sign, node parent) {
		nodes.add(new node(sign, parent));
	}

	public ArrayList<node> getNodes() {
		return nodes;
	}

	public char getSign() {
		return sign;
	}

	public void setSign(char sign) {
		this.sign = sign;
	}

	public node getParent() {
		return parent;
	}

	public void setParent(node parent) {
		this.parent = parent;
	}
	
	public node getNode(char sign) {
		for (int i=0; i<nodes.size(); i++) {
			if (nodes.get(i).getSign()==sign) {
				return nodes.get(i);
			}
		}
		return null;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}
}

public class Leks_Tree {
	private node root;
	private int slowa=0;
	private int nodes=0;
	File file = new File("plik.txt");
	
	public long getFileSize() {
		return file.length()/1024;
	}
	
	public Leks_Tree() {
		root= new node();
	}
	
	public int getSlowa() {
		return slowa;
	}
	
	public int getNodes() {
		return nodes;
	}
	
	public void add(String word)
	{
		slowa++;
		node node = root;
		boolean kont=false;
		char [] tab = word.toCharArray();
		for (int i=0; i<tab.length; i++) 
		{
			for (int j=0; j<node.getNodes().size(); j++) 
			{
				if (node.getNodes().get(j).getSign()==tab[i]) 
				{
					node = node.getNode(tab[i]);
					j=node.getNodes().size();
					kont=true;
				}
			}
			
			if (kont==false) 
			{
				node.add(new node(tab[i], node));
				node.getNode(tab[i]).setParent(node);
				node = node.getNode(tab[i]);
				nodes++;
			}
			if (i==tab.length-1) 
			{
				node.setEnd(true);
			}
			kont=false;
		}
		
	}
	
	public void inorder(node node) {
		if (node == null) {
			return;
		}
		System.out.println(node.getSign());
		for (int i=0; i<node.getNodes().size(); i++) {
			inorder(node.getNodes().get(i));
		}
	}
	
	public node getRoot() {
		return root;
	}
	
	public void sop_topChars() {
		for (int i=0; i<root.getNodes().size(); i++) {
			System.out.print(root.getNodes().get(i).getSign() + " ");
		}
	}
	
	public void sop_Chars(node node) {
		for (int i=0; i<node.getNodes().size(); i++) {
			System.out.print(node.getNodes().get(i).getSign() + " ");
		}
	}
	
	public void load() {
		
		//System.out.println(file.getTotalSpace());
		try {
			BufferedReader read = new BufferedReader(new FileReader(file));
			while(read.ready()) {
				String word = read.readLine();
				add(word);
			}
			read.close();
			
		}
		catch (IOException e) {
			System.out.println("Wyst¹pi³: " + e);
		}
	}
	
	public boolean search(String word) {
		node node = root;
		boolean kont=false;
		char [] tab = word.toCharArray();
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<node.getNodes().size(); j++) {
				if (node.getNodes().get(j).getSign()==tab[i]) {
					node = node.getNode(tab[i]);
					kont=true;
				}
			}
			
			if (kont==false) {
				return false;
			}
			if (i==tab.length-1 && node.isEnd()) {
				return true;
			}
			kont=false;
		}
		return false;
	}
	
	public void words_from_pre(String pre) {
		node node = root;
		boolean empty=false;
		char [] tab = pre.toCharArray();
		for (int i=0; i<tab.length; i++) {
			if (!node.getNodes().isEmpty()) {
				for (int j=0; j<node.getNodes().size(); j++) {
					if (node.getNodes().get(j).getSign()==tab[i]) {
						node = node.getNode(tab[i]);
						j=node.getNodes().size();
					}
					else if (j==node.getNodes().size()-1){
						j=node.getNodes().size();
						empty=true;
						i=tab.length;
					}
				}
				if (empty==true) {
					node=null;
				}
			}
		}
		System.out.println("S³owa które wywodz¹ siê od prefiksu " + pre + " to: ");
		if (node!=null) {
			words(node);
		}
		else {
			System.out.println("Nie ma takich s³ów.");
		}
	}
	
	private void words(node node) {
		if (node==null) {
			return;
		}
		if (node.isEnd()) {
			node nod = node;
			ArrayList<Character> word = new ArrayList<Character>(0);
			while(nod!=root) {
				word.add(0,nod.getSign());
				nod=nod.getParent();
			}
			for (int i=0; i<word.size(); i++) {
				System.out.print(word.get(i));
			}
			System.out.print("\n");
			
		}
		for (int i=0; i<node.getNodes().size(); i++) {
			words(node.getNodes().get(i));
		}
	}
	
	public void most_pre(int n) {
		ArrayList<node> list = new ArrayList<node>(0);
		list.add(root);
		ArrayList<node> list2 = new ArrayList<node>(0);
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<list.size(); j++) {
				for (int k=0; k<list.get(j).getNodes().size(); k++) {
					list2.add(list.get(j).getNodes().get(k));
				}
			}
			list.clear();
			for (int l=0; l<list2.size(); l++) {
				list.add(list2.get(l));
			}
			list2.clear();
		}
		int max=0;
		int index=0;
		int licz=0;
		for (int i=0; i<list.size(); i++) {
			licz=how_many_words(list.get(i));
			if (max<licz) {
				max=licz;
				index=i;
			}
			licz=0;
			licznik=0;
		}
		ArrayList<Character> word = new ArrayList<Character>(0);
		node node = list.get(index);
		while(node!=root) {
			word.add(0,node.getSign());
			node=node.getParent();
		}
		System.out.print("\nNajpopularniejszy prefiks, o d³ugoœci " + n + " to: ");
		for (int i=0; i<word.size(); i++) {
			System.out.print(word.get(i));
		}
		System.out.print("\nJest pocz¹tkiem " + max + " s³ów.");
	}
	
	
	public void most_pre2(int n, node node){
		ArrayList<node> list = new ArrayList<node>(0);
		list.add(node);
		ArrayList<node> list2 = new ArrayList<node>(0);
		/*for (int i=0; i<root.getNodes().size(); i++) {
			list.add(root.getNodes().get(i));
		}*/
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<list.size(); j++) {
				for (int k=0; k<list.get(j).getNodes().size(); k++) {
					list2.add(list.get(j).getNodes().get(k));
				}
			}
			list.clear();
			for (int l=0; l<list2.size(); l++) {
				list.add(list2.get(l));
			}
			list2.clear();
		}
		int max=0;
		int index=0;
		int licz=0;
		for (int i=0; i<list.size(); i++) {
			licz=how_many_words(list.get(i));
			if (max<licz) {
				max=licz;
				index=i;
			}
			licz=0;
			licznik=0;
		}
		
		ArrayList<Character> word = new ArrayList<Character>(0);
		node nod = list.get(index);
		if (nod.getNodes().size()==1 && !nod.isEnd()) {
			most_pre2(1, nod);
		}
		else {
			while(nod!=root) {
				word.add(0,nod.getSign());
				nod=nod.getParent();
			}
			System.out.print("\nNajpopularniejszy prefiks, o zadanej lub wiêkszej d³ugoœci to: ");
			for (int i=0; i<word.size(); i++) {
				System.out.print(word.get(i));
			}
			System.out.print("\nJest pocz¹tkiem " + max + " s³ów.");
		}
		
	}
	
	private int licznik=0;
	
	private int how_many_words(node node) {
		if (node==null) {
			return licznik;
		}
		if (node.isEnd()) {
			licznik++;			
		}
		for (int i=0; i<node.getNodes().size(); i++) {
			how_many_words(node.getNodes().get(i));
		}
		return licznik;
	}
}
