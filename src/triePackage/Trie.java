package triePackage;

import java.util.Iterator;

import mapPackage.IMapFactory;
import actionsPackage.IActionAtInsert;

public class Trie implements ITrie{
	
	private ITrieNode root;
	
	public Trie(IMapFactory mapFactory){
		this.root = new TrieNode(mapFactory, null, null);
	}

	@Override
	public ITrieReference insert(String string, IActionAtInsert value) {
		return root.recursivInsert(string, value);
	}


	@Override
	public ITrieReference insert(Iterator iterator, IActionAtInsert value) {
		return root.recursivInsert(iterator, value);
	}



	@Override
	public ITrieReference lookup(Iterator iterator) {
		return root.recursivInsert(iterator, null);
	}



	@Override
	public ITrieReference lookup(String string) {
		return root.recursivInsert(string, null);
	}


}
