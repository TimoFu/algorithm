package Trie;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Stack;
import java.util.TreeMap;

public class TrieSerialization {
	/**
	 * This method will be invoked first, you should design your own algorithm
	 * to serialize a trie which denote by a root node to a string which can be
	 * easily deserialized by your own "deserialize" method later.
	 */
	public String serialize(TrieNode root) {
		if (root == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append('<');
		for (Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {
			char key = entry.getKey();
			sb.append(key);
			sb.append(serialize(entry.getValue()));
		}
		sb.append('>');
		return sb.toString();
	}

	/**
	 * This method will be invoked second, the argument data is what exactly you
	 * serialized at method "serialize", that means the data is not given by
	 * system, it's given by your own serialize method. So the format of data is
	 * designed by yourself, and deserialize it here as you serialize it in
	 * "serialize" method.
	 */
	public TrieNode deserialize(String data) {
		if (data == null || data.length() == 0) {
			return null;
		}
		TrieNode root = new TrieNode();
		TrieNode current = root;
		Stack<TrieNode> stack = new Stack<TrieNode>();
		for (char c : data.toCharArray()) {
			if (c == '<') {
				stack.push(current);
			} else if (c == '>') {
				stack.pop();
			} else {
				current = new TrieNode();
				stack.peek().children.put(c, current);
			}
		}
		return root;
	}

	public class TrieNode {
		public NavigableMap<Character, TrieNode> children;

		public TrieNode() {
			children = new TreeMap<Character, TrieNode>();
		}
	}

	public static void main(String[] args) {
		TrieSerialization service = new TrieSerialization();
		service.deserialize("<a<b<e<>>c<>d<f<>>>>");
	}
}
