package triePackage;

import java.util.Iterator;

import mapPackage.IMapFactory;
import actionsPackage.IActionAtInsert;

public class Trie implements ITrie {

	private ITrieNode root;
	private IActionAtInsert action;

	public Trie(IMapFactory mapFactory, IActionAtInsert action) {
		this.root = new TrieNode(mapFactory, null, null);
		this.action = action;
	}

	@Override
	public ITrieReference insert(String string) {
		return root.recursivInsert(string, action);
	}

	@Override
	public ITrieReference insert(Iterator iterator) {
		return root.recursivInsert(iterator, action);
	}

	@Override
	public ITrieReference lookup(Iterator iterator) {
		return root.recursivInsert(iterator, null);
	}

	@Override
	public ITrieReference lookup(String string) {
		return root.recursivInsert(string, null);
	}

	public String toString() {
		return root.toString();
	}
}
