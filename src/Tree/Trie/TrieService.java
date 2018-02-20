package Tree.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class TrieService {
	public class TrieNode {
		public NavigableMap<Character, TrieNode> children;
		public List<Integer> top10;

		public TrieNode() {
			children = new TreeMap<Character, TrieNode>();
			top10 = new ArrayList<Integer>();
		}
	}

	private TrieNode root = null;

	public TrieService() {
		root = new TrieNode();
	}

	public TrieNode getRoot() {
		// Return root of trie root, and
		// lintcode will print the tree struct.
		return root;
	}

	// @param word a string
	// @param frequency an integer
	public void insert(String word, int frequency) {
		if (word != null && word.length() != 0) {
			insertHelper(word, frequency, root, 0);
		}
	}

	private void insertHelper(String word, int frequency, TrieNode root, int index) {
		if (word.length() == index) {
			addFrequence(root, frequency);
			return;
		}
		char c = word.charAt(index);
		if (!root.children.containsKey(c)) {
			addFrequence(root, frequency);
			root.children.put(c, new TrieNode());
			for (Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {
				System.out
						.println(word + " " + entry.getKey() + ", " + Arrays.toString(entry.getValue().top10.toArray())
								+ " root.top10=" + Arrays.toString(root.top10.toArray()));
			}
			System.out.println("");
		} else {
			// contains this char
			addFrequence(root, frequency);
			for (Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {
				System.out
						.println(word + " " + entry.getKey() + ", " + Arrays.toString(entry.getValue().top10.toArray())
								+ " root.top10=" + Arrays.toString(root.top10.toArray()));
			}
			System.out.println("");
		}

		insertHelper(word, frequency, root.children.get(c), index + 1);
	}

	private void addFrequence(TrieNode node, int frequence) {

		Set<Integer> freq = new HashSet(node.top10);
		freq.add(frequence);
		List<Integer> list = new ArrayList(freq);
		Collections.sort(list, Collections.reverseOrder());
		node.top10 = list.subList(0, Math.min(10, list.size()));
	}

	public static void main(String[] args) {
		TrieService service = new TrieService();
		service.insert("abc", 2);
		service.insert("ac", 4);
		service.insert("ab", 9);
	}
}