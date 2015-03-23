package triePackage;

import java.util.Iterator;

import actionsPackage.IActionsAtInsert;

public interface ITrieNode {
	
	ITrieReference recursivInsert(Iterator iterator, IActionsAtInsert actionAtInsert);

}
