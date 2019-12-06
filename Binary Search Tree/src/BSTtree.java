import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

class BSTnode{
	private BSTnode parent;
	private BSTnode son_left;
	private BSTnode son_right;
	private String word;
	private char key;
	
	public BSTnode() {
	}
	
	public BSTnode(String word, BSTnode node) {
		this.word=word;
		this.key=word.charAt(0);
		son_left=null;
		son_right=null;
		parent=node;
		
	}

	public BSTnode getParent() {
		return parent;
	}

	public void setParent(BSTnode parent) {
		this.parent = parent;
	}

	public BSTnode getSon_left() {
		return son_left;
	}

	public void setSon_left(BSTnode son_left) {
		this.son_left = son_left;
	}

	public BSTnode getSon_right() {
		return son_right;
	}

	public void setSon_right(BSTnode son_right) {
		this.son_right = son_right;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}
	
	public boolean isEmpty() {
		if (word.isEmpty()) {
			return true;
		}
		return false;
	}
	
}

class Pos{
	BSTnode node;
	int position;
	
	public Pos(BSTnode node, int position) {
		this.node=node;
		this.position=position;
	}
}

class Miejsce{
	BSTnode node;
	int wysokosc;
	int pozycja;
	public Miejsce(BSTnode node, int wysokosc, int pozycja) {
		this.node=node;
		this.wysokosc=wysokosc;
		this.pozycja=pozycja;
	}
}

public class BSTtree {
	private BSTnode root;
	
	public BSTtree(BSTnode node) {
		root = node;
	}
	
	public void add(String word) {
		BSTnode node = root;
		char key = word.charAt(0);
		while(node.getWord().equals(word)==false) {
			if (key<=node.getKey()) {
				if (node.getSon_left()==null) {
					node.setSon_left(new BSTnode(word, node));
				}
				node=node.getSon_left();
			}
			else {
				if (node.getSon_right()==null) {
					node.setSon_right(new BSTnode(word, node));
				}
				node=node.getSon_right();
			}
		}
	}
	
	public void delete(String word) {
		BSTnode node = search_for(word);
		if (node!=null) {
			if (node.getSon_left()==null && node.getSon_right()==null) {
				node=null;
			}
			else if (node.getSon_left()!=null && node.getSon_right()!=null) {
				BSTnode node_delete = node;
				node = node.getSon_right();
				while (node.getSon_left()!=null) {
					node = node.getSon_left();
				}
				if (node_delete.getParent()!=null) {
					node_delete.getParent().setSon_right(node);
					node.getParent().setSon_right(node.getSon_right());
					node.setSon_left(node_delete.getSon_left());
					node.setSon_right(node_delete.getSon_right());
				}
				else {
					node.getParent().setSon_right(node.getSon_right());
					node.setSon_left(node_delete.getSon_left());
					node.setSon_right(node_delete.getSon_right());
					node.setParent(null);
					root=node;
				}
			}
			else if (node.getSon_left()!=null) {
				node.getParent().setSon_left(node.getSon_left());
			}
			else {
				node.getParent().setSon_right(node.getSon_right());
			}
			
		}
	}
	
	public boolean search(String word) {
		BSTnode node = search_for(word);
		if (node==null) {
			return false;
		}
		return true;
	}
	
	private BSTnode search_for(String word) {
		BSTnode node = root;
		char key = word.charAt(0);
		while(!node.getWord().equals(word) || (node.getSon_left()==null && node.getSon_right()==null)) {
			if (key<=node.getKey()) {
				if (node.getSon_left()!=null) {
					node=node.getSon_left();
				}
				else return null;
				
			}
			else {
				if (node.getSon_right()!=null) {
					node=node.getSon_right();
				}
				else return null;
				
			}
		}
		return node;
	}
	
	public void BSTsave() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("plik.txt"));
			BSTnode node = root;
			Queue<BSTnode> queue = new LinkedList<BSTnode>();
			queue.add(node);
			
			while (!queue.isEmpty()) {
				node = queue.remove();
				writer.write(node.getWord());
				writer.newLine();
				
				if (node.getSon_left()!=null) {
					queue.add(node.getSon_left());
				}
				if (node.getSon_right()!=null) {
					queue.add(node.getSon_right());
				}
			}
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Wyst¹pi³: " + e);
		}
	}
	
	public void BSTload(File file) {
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
	
	public void BSTinorder(BSTnode node) {
		if (node == null) {
			return;
		}
		BSTinorder(node.getSon_left());
		System.out.println(node.getWord());
		BSTinorder(node.getSon_right());
	}
	
	public void BSTpreorder(BSTnode node) {
		if (node == null) {
			return;
		}
		System.out.println(node.getWord());
		BSTinorder(node.getSon_left());
		BSTinorder(node.getSon_right());
	}
	
	public void BSTpostorder(BSTnode node) {
		if (node == null) {
			return;
		}
		BSTinorder(node.getSon_left());
		BSTinorder(node.getSon_right());
		System.out.println(node.getWord());
	}
	
	public void BSTtop_down(){
		ArrayList<Integer> positions = new ArrayList<Integer>(0);
		Queue<Pos> queue = new LinkedList<Pos>();
		queue.add(new Pos(root, 0));
		
		while (!queue.isEmpty()) {
			Pos pos = queue.remove();
			int miejsce = pos.position;
			BSTnode node = pos.node;
			
			if (!positions.contains(miejsce)) {
				positions.add(miejsce);
				System.out.print(node.getWord()+ " ");
			}
			
			if (node.getSon_left()!=null) {
				queue.add(new Pos(node.getSon_left(), miejsce-1));
			}
			if (node.getSon_right()!=null) {
				queue.add(new Pos(node.getSon_right(), miejsce+1));
			}
		}
	}
	
	 public void BSTbottom_up() {
		 ArrayList<Miejsce> list = new ArrayList<Miejsce>(0);
		 Queue<Miejsce> queue = new LinkedList<Miejsce>();
		 int wysokosc=0, miejsce=0;
		 queue.add(new Miejsce(root, 0, 0));
		 list.add(queue.peek());
		 while(!queue.isEmpty()) {
			for (int i=0; i<list.size(); i++) {
				 Miejsce miej = list.get(i);
				 Miejsce peek = queue.peek();
				 if (miej.pozycja==peek.pozycja && miej.wysokosc<peek.wysokosc) {
					 list.remove(i);
					 list.add(i, peek);
					 i=list.size();
				 }
				 else if (i==list.size()-1 && miej.pozycja!=peek.pozycja) {
					 list.add(peek);
					 i=list.size();
				 }
			}
			 
			 BSTnode node = queue.peek().node;
			 wysokosc=queue.peek().wysokosc;
			 miejsce=queue.remove().pozycja; 			 
			 
			 if (node.getSon_left()!=null) {
				 queue.add(new Miejsce(node.getSon_left(), wysokosc+1, miejsce-1));
			 }
			 if (node.getSon_right()!=null) {
				 queue.add(new Miejsce(node.getSon_right(), wysokosc+1, miejsce+1));
			 }
			 
		 }
		 
		 for (int i=0; i<list.size(); i++) {
			 System.out.print(list.get(i).node.getWord()+ " ");
		 }
		 
		 
	 }
	 
	 public void BSTstructure() {
		 ArrayList<Miejsce> list = new ArrayList<Miejsce>(0);
		 Queue<Miejsce> queue = new LinkedList<Miejsce>();
		 int wysokosc=0, miejsce=0;
		 queue.add(new Miejsce(root, 0, 0));
		// list.add(queue.peek());
		 while(!queue.isEmpty()) {
			 
			 BSTnode node = queue.peek().node;
			 wysokosc=queue.peek().wysokosc;
			 miejsce=queue.remove().pozycja; 			 
			 list.add(new Miejsce(node, wysokosc, miejsce));
			 
			 if (node.getSon_left()!=null) {
				 queue.add(new Miejsce(node.getSon_left(), wysokosc+1, miejsce-1));
			 }
			 if (node.getSon_right()!=null) {
				 queue.add(new Miejsce(node.getSon_right(), wysokosc+1, miejsce+1));
			 }
			 
		 }
		 
		 int height=0;
		 for (int i=0; i<list.size(); i++) {
			 if (height==list.get(i).wysokosc-1) {
				 System.out.print("\n");
			 }
			 System.out.print(list.get(i).node.getWord() + " ");
			 
			 height=list.get(i).wysokosc;
		 }		 
	 }
	    

}
