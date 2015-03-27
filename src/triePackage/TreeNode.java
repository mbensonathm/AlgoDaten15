package triePackage;

import java.util.Iterator;
import java.util.Map;

import mapPackage.IMapFactory;
import actionsPackage.IActionsAtInsert;

public class TreeNode implements ITrieNode{
	final private Map outgoingEdgeMap;
	final private ITrieNode parent;
	final private Comparable incomingPartialKey;
	final private IMapFactory mapFactory;
	
	public TreeNode(IMapFactory mapFactory, ITrieNode parent, Comparable incomingPartialKey){
		this.mapFactory = mapFactory;
		this.parent = parent;
		this.incomingPartialKey = incomingPartialKey;
		this.outgoingEdgeMap = this.mapFactory.create();
	}
	@Override
	public ITrieReference recursivInsert(Iterator iterator,
			IActionsAtInsert actionAtInsert) {
		// TODO Auto-generated method stub
		return null;
	}

}
