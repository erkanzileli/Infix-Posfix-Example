public class MainClass {

	public static void main(String[] args) {
		System.out.println(calculate(getPostfixString("(5-3)*2-1*(9-6)")));
	}
	

	private static int calculate(String postfix) {
		MyStack ms = new MyStack();
		int sayi1 = 0;
		int sayi2 = 0;
		for (int i = 0; i < postfix.length(); i++) {
			// Operat�r
			if (postfix.charAt(i) == '+' || postfix.charAt(i) == '-' || postfix.charAt(i) == '*'
					|| postfix.charAt(i) == '/' || postfix.charAt(i) == '^') {
				sayi1 = Integer.parseInt(ms.pop());
				sayi2 = Integer.parseInt(ms.pop());
				switch (postfix.charAt(i)) {
				case '+':
					ms.push(String.valueOf(sayi1 + sayi2));
					break;
				case '-':
					ms.push(String.valueOf(sayi2 - sayi1));
					break;
				case '*':
					ms.push(String.valueOf(sayi1 * sayi2));
					break;
				case '/':
					ms.push(String.valueOf(sayi2 / sayi1));
					break;
				case '^':
					ms.push(String.valueOf(Math.pow(sayi2, sayi1)));
					break;
				}
			}
			// Operant
			else {
				ms.push(String.valueOf(postfix.charAt(i)));
			}
		}
		return Integer.parseInt(ms.pop());
	}

	private static int getOncelik(char islem) {
		if (islem == '^') {
			return 3;
		} else if (islem == '*' || islem == '/') {
			return 2;
		} else if (islem == '+' || islem == '-') {
			return 1;
		}
		// '('
		else {
			return 0;
		}
	}

	private static String getPostfixString(String s) {
		String postfix = "";
		int islem, bas;
		LinkedStack ls = new LinkedStack();
		char c = ' ';
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/'
					|| s.charAt(i) == '^') {
				islem = getOncelik(s.charAt(i));
				bas = getOncelik(ls.getBas());
				// parantez a�ma var ise push
				// y���n bo� ise push
				// stackde operator varsa �ncelik kar��la�t�r�l�r
				// okunan operator stackdeki operatorde d���k veya e�it
				// �ncelikli ise
				// stackdekilerin hepsi postfixe okunan stack'e
				// okunan operator stackdeki operatorden y�ksek �ncelikli ise
				// okunan stackdekinin �st�ne push
				if (ls.getBas() == '(' || ls.getBas() == ' ') {
					ls.push(s.charAt(i));
				} else if (islem > bas) {
					ls.push(s.charAt(i));
				} else if (islem < bas) {
					if (islem == 2) {
						while (true) {
							c = ls.getBas();
							if (c == '*' || c == '/' || c == '^') {
								postfix += c;
								ls.pop();
							} else {
								ls.push(s.charAt(i));
								break;
							}
						}
					} else {
						while (true) {
							c = ls.getBas();
							if (c == '+' || c == '*' || c == '/' || c == '-' || c == '^') {
								postfix += c;
								ls.pop();
							} else {
								ls.push(s.charAt(i));
								break;
							}
						}
					}
				} else if (islem == bas) {
					if (islem == 3) {
						while (true) {
							c = ls.getBas();
							if (c == '^') {
								postfix += c;
								ls.pop();
							} else {
								ls.push(s.charAt(i));
								break;
							}
						}
					} else if (islem == 2) {
						while (true) {
							c = ls.getBas();
							if (c == '*' || c == '/' || c == '^') {
								postfix += c;
								ls.pop();
							} else {
								ls.push(s.charAt(i));
								break;
							}
						}
					} else {
						while (true) {
							c = ls.getBas();
							if (c == '+' || c == '*' || c == '/' || c == '-' || c == '^') {
								postfix += c;
								ls.pop();
							} else {
								ls.push(s.charAt(i));
								break;
							}
						}
					}
				}
			} else if (s.charAt(i) == '(') {
				ls.push(s.charAt(i));
			} else if (s.charAt(i) == ')') {
				// parantez a�maya kadar b�t�n stackler pop edilip postfixe
				// sonra parantez a� ��kar�l�r
				while (true) {
					c = ls.pop();
					if (c == '(' || c == ' ') {
						break;
					} else {
						postfix += c;
					}
				}
			} else {
				postfix += s.charAt(i);
			}
		}
		// d�ng� bitti�inde s�rayla pop yap�larak postfixe eklenir
		while (true) {
			c = ls.getBas();
			if (c != ' ') {
				postfix += ls.pop();
			} else {
				break;
			}
		}
		return postfix;
	}
}