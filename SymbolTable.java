import java.util.ArrayList;

public class SymbolTable<Key extends Comparable<Key>, Value> { //specify generics
	final boolean RED = true;
	final boolean BLACK = false;
	public Node root = null;
	// how to make generic?

	public SymbolTable() {
        
	}
	
	public void put(Key k, Value v) { //need second method because we need to fix links
		root = put(root, k, v); //int?
		root.color = BLACK;
		root.add(); //increase size
	}
	
	private Node put(Node x, Key k, Value v) {
		if (x == null) {
			return new Node(k, v); //if nothing there, attach
		}
		
		int comp = k.compareTo(x.key);
		if (comp < 0) { //if less than
			x.left = put(x.left, k, v); //search left tree
		} else if (comp > 0) { //if greater than
			x.right = put(x.right, k, v); //search right tree
		} else { //if same
			x.value = v; //change value
		}
        
		fixUp(x);
        
        x.size = size(x.left) + size(x.right) + 1;
		
		return x;
		
		
	}
    
    
    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) { //why does this work?
			leftRot(x);
		}
		if (isRed(x.left) && isRed(x.left.left)) {
			rightRot(x);
		}
		if (isRed(x.left) && isRed(x.right)) {
			flipColors(x);
        }
        
        return x;
    }
	
	public Value get(Key k) {
		return get(root, k);
	}
	
	private Value get(Node x, Key k) {
		if (x == null) return null; //if hit dead end
		
		int comp = k.compareTo(x.key);
		
		if (comp < 0) {
			return get(x.left, k);
		} else if (comp > 0) {
			return get(x.right, k);
		} else {
			return x.value;
		}

	}
	
	public Node leftRot(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		
		x.color = h.color;
		h.color = RED;
		
        int temp = x.size;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right); //h size changing but it's childrens' sizes haven't
        
        //System.out.println("Left rotation");
        //System.out.println(x.value + " " + size(x));
        //System.out.println(h.value + " " + size(h));
		return x;
	}
	
	public Node rightRot(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		
		x.color = h.color;
		h.color = RED;
        
        x.size = h.size;
        h.size =  1 + size(h.left) + size(h.right); //save x.size in a temp instead?
		
		return x;
	}
	
	private boolean isRed(Node h) {
        if (h == null) {
            return false;
        }
		return h.color;
	}
    
    public int size(Node h) {
        if (h == null) {
            return 0;
        }
        return h.size;
    }
	
	private void flipColors(Node h) { //is there a more concise way to do this?
		if (h.color == RED) {
			h.color = BLACK;
			h.left.color = RED;
			h.right.color = RED;
		} else {
			h.color = RED;
			h.left.color = BLACK;
			h.right.color = BLACK;
		}
	}
	
    public void deleteMin() {
        root = deleteMin(root);
        root.color = BLACK;
    }
    
    private Node deleteMin(Node h) {
        if (h.left == null) return null; //when hit bottom, delete previous node
        
        if (!isRed(h.left) && !isRed(h.left.left)) { //two black in a row
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return fixUp(h); //go back and fix moves, similar to put
    }
    
    private Node moveRedLeft(Node h) { //move red to where it can be deleted
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rightRot(h.right);
            h = leftRot(h);
            flipColors(h);
        }
        return h;
    }
    
    private Node delete(Node h, Key k) {
        if (h == null) return null;
        
        int comp = k.compareTo(h.k);
        if (comp < 0) {
            if(!isRed(h.left) && !isRed(h.left.left() {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) {
                h.rightRot(h);
            }
        }
        
    }
    
   /*  private Node delete(Node h) {
        Node x = h.left;
        Node temp = x.left;
        leftRot(h);
        h.right = temp;
        h.left = delete(h.left);
    } */
    
	/* private Node max (Node x) {
		while (x.right != null) {//as long as there is a child
			x = x.right; //search right child
		}
		return x; //return the childless (biggest) node
	}
	
	private Node min (Node x) {
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}
	
	
	public void delete(Key k) {
		root = delete(root, k);
	}
	
	private void delete(Node x, Key k) { //returns a tree
		if (x == null) return null;
		
		int cmp = k.compareTo(x.key);
		if (cmp == 0) { //if found key
			if (x.right == null) return x.left; //search other child
			if (x.left == null) return x.right; 
			Node temp = x;
			x = min(t.right); // x becomes the min of the right link
			x.right = deleteMin(t.right); // make x.right the right link of the min
			
		} else if (cmp < 0) {
			x.left = delete(x.left, k); //x.left becomes the tree with k deleted
		} else if (comp > 0) {
			x.right = delte(x.right, k);
		}
		
		return delete(temp);
		
	}
	
	//public Node floor(Node x) { //greatest node that is less
		
	//}

	
	//public Node ceiling(Node x) { //smallest node that is greater
	
	//}
	
	public Key select(int i) { //the kth smallest key (in the whole tree)
		
		return select(root, i);
	
	}
	
	private Node select(Node x, int i) {
		if (x == null) return null;
		
		int s = size(x.left); //keys less than x + 1
		if (s == i) {
			return x.k;
		} else if (s < i) { //if node size is less than, search right
			return select(x.right, i-s-1); //don't have to satisfy parent.left, since we are subtracting it (and -1 for initial root)
		} else if (s > k) { //if node size is greater than, search left
			return select(x.left, i);
		}
	}
	
	//public int rank(Key k) { //reverse of select
		//return rank(root);
	//}
	
	private Node rank() {
		
	}
	
	
	*/
	
	private class Node implements Comparable<Node> {
		public Key key; // fields from the innerclass are public throughout the file
		public Value value;
		public Node left;
		public Node right;
		public boolean color;
		public int size;
		
		public Node(Key k, Value v) {
			this.key = k;
			this.value = v;
			this.color = RED;
            this.size = 1;
		}
		
		public int compareTo(Node that) {
			return this.key.compareTo(that.key);
		}
		
		public void add() {
			size += 1;
		}
        
	} 
	
	public static void main(String[] args) {
		SymbolTable test = new SymbolTable();
		test.put("s", 1);
		test.put("e", 2);
		test.put("a", 3);
		test.put("r", 4);
		test.put("c", 5);
		test.put("h", 6);
		test.put("e", 7);
		test.put("x", 8);
		test.put("a", 9);
		test.put("m", 10);
        System.out.println(test.get("a"));
        test.deleteMin();
		//System.out.println(test.get("s"));
		//System.out.println(test.get("e"));
		/* System.out.println(test.get("a"));
		System.out.println(test.get("m")); */
	}
}