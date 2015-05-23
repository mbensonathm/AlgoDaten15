package triePackage;

import java.util.Iterator;
import java.util.TreeMap;

import token.IToken;
import mapPackage.IMapFactory;
import actionsPackage.IActionAtInsert;

public class Trie implements ITrie {

	private ITrieNode root;
	private IActionAtInsert action;
	private TreeMap<Integer, String> clearText = new TreeMap<Integer, String>();

	public Trie(IMapFactory mapFactory, IActionAtInsert action) {
		this.root = new TrieNode(mapFactory, null, null);
		this.action = action;
	}

	@Override
	public ITrieReference insert(String string) {
		ITrieReference ref = root.recursivInsert(string, action);
		try{
			clearText.put((Integer) ref.getValue(), string);
		} catch (Exception e)
		{
			// Exception not handled.
		}
		return ref;
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

	@Override
	public TreeMap<Integer, String> getClearText() {
		return this.clearText;
	}
}
