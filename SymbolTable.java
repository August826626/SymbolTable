public class SymbolTable {

	// how to make generic?

	public SymbolTable() {
		
	}
	
	public void put(Key k, Value v) { //need second method because we need to fix links
		int root = put(root, k, v); //int?
	}
	
	private Node put(Node x, Key k, Value v) {
		if (x == null) {
			return new Node(k, v); //if nothing there, attach
		}
		
		int comp = k.compareTo(x.k);
		if (comp == 0) { //if same
			return null;
		} else if (comp < 0) { //if less than
			x.left = put(x.left, k, v); //search left tree
			return x; //go back up and fix link
		} else if (comp > 0) { //if greater than
			x.right = put(x.right, k, v);
			return x;			
		}
	}
	
	public Node max (Node x) {
		while (x.right != null) {//as long as there is a child
			x = x.right; //search right child
		}
		return x; //return the childless (biggest) node
	}
	
	public Node min (Node x) {
		while (x.left != null) {
			x = x.left;
		}
		return x;
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
		} else if (s < i) {
			return select(x.right, i);
		} else if (s > k) {
			return select(x.left, i-s-1); //no longer have to satisfy parent.left, since we are subtracting it
		}
	}
	
	private int size(Node x) { //number of nodes less than current node?
		if (x == null) {
			return 0;
		} else {
			return size(x.left);
		}
		
	}
	
	public static void main(String[] args) {
		SymbolTable test = new SymbolTable();
		test.put(s, 1);
		test.put(e, 2);
		test.put(x, 3);
	}
}