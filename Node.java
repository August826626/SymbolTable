import java.util.ArrayList;

public class Node implements Comparable<Node> {
	public Key k;
	public Value v;
	public Node parent;
	
	public Node(Key k, Value v) {
		this.key = k;
		this.value = v;
	}
	
	public int compareTo(Node that) {
		return this.k.compareTo(that.k);
	}
}