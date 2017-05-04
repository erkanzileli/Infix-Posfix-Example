
public class MyStack {
	MyObject bas;

	public MyStack() {
		bas = null;
	}

	public void push(String s) {
		MyObject obj = new MyObject(s);
		if (bas != null) {
			obj.ileri = bas;
			bas = obj;
		} else {
			bas = obj;
		}
	}

	public String getBas() {
		return bas != null ? bas.s : " ";
	}

	public String pop() {
		if (bas != null) {
			MyObject obj = bas;
			bas = bas.ileri;
			return obj.s;
		}
		return " ";
	}
}
