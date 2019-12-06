import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.io.File;


class node{
	private node parent;
	private node son_left;
	private node son_right;
	private String word;
	
	public node() {
		
	}
	
	public node(String word, node node) {
		this.word=word;
		son_left=null;
		son_right=null;
		parent=node;
		
	}

	public node getParent() {
		return parent;
	}

	public void setParent(node parent) {
		this.parent = parent;
	}

	public node getSon_left() {
		return son_left;
	}

	public void setSon_left(node son_left) {
		this.son_left = son_left;
	}

	public node getSon_right() {
		return son_right;
	}

	public void setSon_right(node son_right) {
		this.son_right = son_right;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public boolean isEmpty() {
		if (word.isEmpty()) {
			return true;
		}
		return false;
	}
	
}

class Pos2{
	node node;
	int position;
	
	public Pos2(node node, int position) {
		this.node=node;
		this.position=position;
	}
}

class Miejsce2{
	node node;
	int wysokosc;
	int pozycja;
	public Miejsce2(node node, int wysokosc, int pozycja) {
		this.node=node;
		this.wysokosc=wysokosc;
		this.pozycja=pozycja;
	}
}

public class tree {
	private node root;

	
	
	public tree(node node) {
		root = node;
	}
	
	public void add(String word) {
		node node = root;
		while(node.getWord().equals(word)==false) {
			Random rand = new Random();
			int R = rand.nextInt(2);
			if (R==0) {
				if (node.getSon_left()==null) {
					node.setSon_left(new node(word, node));
				}
				node=node.getSon_left();
			}
			else {
				if (node.getSon_right()==null) {
					node.setSon_right(new node(word, node));
				}
				node=node.getSon_right();
			}
		}
	}
	
	public void delete(String word) {
		node node = search_for(word);
		if (node!=null) {
			if (node.getSon_left()==null && node.getSon_right()==null) {
				node=null;
			}
			else if (node.getSon_left()!=null && node.getSon_right()!=null) {
				node node_delete = node;
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
				node.getSon_left().setParent(node.getParent());
				node.getParent().setSon_left(node.getSon_left());
			}
			else {
				node.getSon_right().setParent(node.getParent());
				node.getParent().setSon_right(node.getSon_right());
			}
			
		}
	}
	
	public boolean search(String word) {
		node node = search_for(word);
		if (node==null) {
			return false;
		}
		return true;
	}
	
	private node search_for(String word) {
		node node = root;
		Queue<node> queue = new LinkedList<node>();
		queue.add(node);
		
		while (!queue.isEmpty()) {
			node = queue.remove();
			if (node.getWord().equals(word)) {
				return node;
			}
			
			if (node.getSon_left()!=null) {
				queue.add(node.getSon_left());
			}
			if (node.getSon_right()!=null) {
				queue.add(node.getSon_right());
			}
		}
		return null;
	}
	
	public void save() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("plik.txt"));
			node node = root;
			Queue<node> queue = new LinkedList<node>();
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
	
	public void load(File file) {
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
	
	public void top_down(){
		ArrayList<Integer> positions = new ArrayList<Integer>(0);
		Queue<Pos2> queue = new LinkedList<Pos2>();
		queue.add(new Pos2(root, 0));
		
		while (!queue.isEmpty()) {
			Pos2 pos = queue.remove();
			int miejsce = pos.position;
			node node = pos.node;
			
			if (!positions.contains(miejsce)) {
				positions.add(miejsce);
				System.out.print(node.getWord()+ " ");
			}
			
			if (node.getSon_left()!=null) {
				queue.add(new Pos2(node.getSon_left(), miejsce-1));
			}
			if (node.getSon_right()!=null) {
				queue.add(new Pos2(node.getSon_right(), miejsce+1));
			}
		}
	}
	
	 public void bottom_up() {
		 ArrayList<Miejsce2> list = new ArrayList<Miejsce2>(0);
		 Queue<Miejsce2> queue = new LinkedList<Miejsce2>();
		 int wysokosc=0, miejsce=0;
		 queue.add(new Miejsce2(root, 0, 0));
		 list.add(queue.peek());
		 while(!queue.isEmpty()) {
			for (int i=0; i<list.size(); i++) {
				 Miejsce2 miej = list.get(i);
				 Miejsce2 peek = queue.peek();
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
			 
			 node node = queue.peek().node;
			 wysokosc=queue.peek().wysokosc;
			 miejsce=queue.remove().pozycja; 			 
			 
			 if (node.getSon_left()!=null) {
				 queue.add(new Miejsce2(node.getSon_left(), wysokosc+1, miejsce-1));
			 }
			 if (node.getSon_right()!=null) {
				 queue.add(new Miejsce2(node.getSon_right(), wysokosc+1, miejsce+1));
			 }
			 
		 }
		 
		 for (int i=0; i<list.size(); i++) {
			 System.out.print(list.get(i).node.getWord()+ " ");
		 }
		 
		 
	 }
	 
	 public void structure() {
		 ArrayList<Miejsce2> list = new ArrayList<Miejsce2>(0);
		 Queue<Miejsce2> queue = new LinkedList<Miejsce2>();
		 int wysokosc=0, miejsce=0;
		 queue.add(new Miejsce2(root, 0, 0));
		// list.add(queue.peek());
		 while(!queue.isEmpty()) {
			 
			 node node = queue.peek().node;
			 wysokosc=queue.peek().wysokosc;
			 miejsce=queue.remove().pozycja; 			 
			 list.add(new Miejsce2(node, wysokosc, miejsce));
			 
			 if (node.getSon_left()!=null) {
				 queue.add(new Miejsce2(node.getSon_left(), wysokosc+1, miejsce-1));
			 }
			 if (node.getSon_right()!=null) {
				 queue.add(new Miejsce2(node.getSon_right(), wysokosc+1, miejsce+1));
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

