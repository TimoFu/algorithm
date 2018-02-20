package Tree.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Typeahead {

	private HashMap<String, Set<String>> map = new HashMap<String, Set<String>>();

	public Typeahead() {

	}

	// @param dict A dictionary of words dict
	public Typeahead(Set<String> dict) {
		// do initialize if necessary
		for (String str : dict) {
			for (int i = 0; i < str.length(); i++) {
				for (int j = i + 1; j < str.length(); j++) {
					String word = str.substring(i, j);
					if (map.containsKey(word)) {
						map.get(word).add(str);
					} else {
						map.put(word, new HashSet<String>());
						map.get(word).add(str);
					}
				}
			}
		}
	}

	// @param str: a string
	// @return a list of words
	public List<String> search(String str) {

		if (map.containsKey(str)) {
			return new ArrayList(map.get(str));
		} else {
			return new ArrayList<String>();
		}
	}

	public static void main(String[] args) {

		Set<String> set = new HashSet<String>();
		set.add("Jason Zhang");
		set.add("James Yu");
		set.add("Li Zhang");
		set.add("Yanxin shi");
		Typeahead service = new Typeahead(set);
		service.search("Zhang");
	}
}