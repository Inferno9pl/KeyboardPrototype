package models;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree implements Serializable {
	private static final long serialVersionUID = 8588274058658262268L;

	public class Node implements Serializable {
		private static final long serialVersionUID = -4091644521878668294L;

		private String myId; // dla testow aby sprawdzac czy po dobrych nodach przechodzimy
		private Node[] children = new Node[5]; // odnogi do innych nodow
		private List<String> words = new ArrayList<String>(); // wyrazy jakie zawiera i sa juz skonczone
		public boolean hasWords = false; // czy zawiera wyrazy

		// root
		public Node() {
			this.myId = "x";
		}

		public Node(String myId, int fingerNumber) {
			this.myId = myId + fingerNumber;
		}

		public String getId() {
			return this.myId;
		}

		public Node getChild(int index) {
			return this.children[index];
		}

		public String getWords() {
			return this.words.toString();
		}
	}

	// ########## DRZEWO ##########
	private Node root;
	private Map<Character, Integer> LetterFingerMap = new HashMap<>();

	private char[] finger1;
	private char[] finger2;
	private char[] finger3;
	private char[] finger4;
	private char[] finger5;

	public Tree(char[] finger1, char[] finger2, char[] finger3, char[] finger4, char[] finger5) {
		this.root = new Node();

		this.finger1 = finger1;
		this.finger2 = finger2;
		this.finger3 = finger3;
		this.finger4 = finger4;
		this.finger5 = finger5;

		createMap();
	}

	// tworzy mape przypisujaca znaki do odpowienich indeksow (boksow)
	private void createMap() {
		int i;
		for (i = 0; i < this.finger1.length; i++)
			LetterFingerMap.put(finger1[i], 1);
		for (i = 0; i < this.finger2.length; i++)
			LetterFingerMap.put(finger2[i], 2);
		for (i = 0; i < this.finger3.length; i++)
			LetterFingerMap.put(finger3[i], 3);
		for (i = 0; i < this.finger4.length; i++)
			LetterFingerMap.put(finger4[i], 4);
		for (i = 0; i < this.finger5.length; i++)
			LetterFingerMap.put(finger5[i], 5);
	}

	// dodaje wyraz do drzewa w odpowienim miejscu
	public void addWord(String word) {
		int i;
		char letters[] = word.toCharArray(); // rozwala string na pojedyncze litery

		Node actualNode = this.root; // obecny Node w ktorym jestesmy, a zaczynamy od roota
		int index; // numer dziecka (albo inaczej numer palca, pod ktorym znajduje sie dana litera)
		int correctLetters = 0; // ilosc istniejacych u nas liter w wyrazie

		// sprawdzam czy wyraz nie zawiera liter ktorych nie mamy
		for (i = 0; i < letters.length; i++) {
			if (LetterFingerMap.containsKey(letters[i])) {
				correctLetters = correctLetters + 1;
			} else
				break;
		}

		// jesli wszystkie litery sa poprawne to tworzy wezly
		if (correctLetters == word.length()) {
			// dla kazdej litery tworzy wezel
			for (i = 0; i < letters.length; i++) {
				index = LetterFingerMap.get(letters[i]); // pobieram index
				actualNode = addNode(actualNode, index); // tworze wezel do aktualnego wezla i przechodze do niego od razu
			}

			// gdy zbior wyrazow nie jest pusty to moge sprawdzic czy juz istnieje taki wyraz
			if (!actualNode.words.isEmpty()) {
				int k;
				boolean wasBreak = false;

				for (k = 0; k < actualNode.words.size(); k++) {
					if (actualNode.words.get(k).equals(word)) {
						wasBreak = true;
						break;
					}
				}

				if (!wasBreak)
					actualNode.words.add(word); // gdy doda ostatnia litere to dodaje caly wyraz do stworzonego wezla (gdy nie wystepuje juz)
			} else
				actualNode.words.add(word);

			actualNode.hasWords = true;
		}
	}

	// dodaje do drzewa caly slownik pobrany z pliku
	public void addDictionary2Tree(String name) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(name), "UTF-8"));
		try {
			String line = br.readLine();
			while (line != null) {
				String[] temp = line.split(", ");
				for (int i = 0; i < temp.length; i++) {
					this.addWord(temp[i].toLowerCase());
				}
				line = br.readLine();
			}
		} finally {
			br.close();
			System.out.println("Pobrano slownik z pliku i stworzono drzewo z niego");
		}
	}

	// tworzy nowe dziecko i zwraca do niego referencje
	private Node addNode(Node actualNode, int childIndex) {
		// jesli juz jest to nie tworzy i zwraca juz obecny wezel
		if (actualNode.children[childIndex - 1] != null)
			return actualNode.children[childIndex - 1];
		else {
			String myId = actualNode.getId();
			return actualNode.children[childIndex - 1] = new Node(myId, childIndex);
		}
	}

	// zapisuje serializowane drzewo na dysku
	public void saveTree(Tree tree, String name) throws IOException {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(name + ".bin"))) {
			outputStream.writeObject(tree);
			System.out.println("Drzewo zapisane");
		}
	}

	// wczytuje drzewo z dysku
	public Tree loadTree(String name) throws ClassNotFoundException, IOException {
		Tree tree = null;
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(name + ".bin"))) {
			tree = (Tree) inputStream.readObject();
			System.out.println("Drzewo zaladowane");
			return tree;
		}
	}

	public Node getRoot() {
		return root;
	}

}
