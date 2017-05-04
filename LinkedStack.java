
public class LinkedStack {
	Node bas;

	public LinkedStack() {
		bas = null;
	}

	public void push(char islem) {
		Node node = new Node(islem);
		if (bas != null) {
			node.ileri = bas;
			bas = node;
		} else {
			bas = node;
		}
	}

	public char getBas() {
		return bas != null ? bas.islem : ' ';
	}

	public char pop() {
		if (bas != null) {
			Node node = bas;
			bas = bas.ileri;
			return node.islem;
		}
		return ' ';
	}
}
