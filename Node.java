import java.util.ArrayList;

private class Node implements Comparable<Node> {
	public Key k; // fields from the innerclass are public throughout the file
	public Value v;
	public Node parent;
	public int size;
	
	public Node(Key k, Value v) {
		this.key = k;
		this.value = v;
	}
	
	public int compareTo(Node that) {
		return this.k.compareTo(that.k);
	}
	
	public void add() {
		size += 1;
	}
}