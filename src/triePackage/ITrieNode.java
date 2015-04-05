package triePackage;

import java.util.Iterator;

import actionsPackage.IActionAtInsert;

public interface ITrieNode {
	
	ITrieReference recursivInsert(Iterator iterator, IActionAtInsert actionAtInsert);
	
	ITrieReference recursivInsert(String s, IActionAtInsert actionAtInsert);

}
