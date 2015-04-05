package triePackage;

import java.util.Iterator;
import java.util.Map;

import mapPackage.IMapFactory;
import actionsPackage.IActionAtInsert;

public class TrieNode implements ITrieNode{
	final private Map<Comparable, ITrieNode> outgoingEdgeMap;
	final private ITrieNode parent;
	final private Comparable incomingPartialKey;
	final private IMapFactory mapFactory;
	private Object value;
	
	public TrieNode(IMapFactory mapFactory, ITrieNode parent, Comparable incomingPartialKey){
		this.mapFactory = mapFactory;
		this.parent = parent;
		this.incomingPartialKey = incomingPartialKey;
		this.outgoingEdgeMap = this.mapFactory.create();
	}
	@Override
	public ITrieReference recursivInsert(Iterator<Comparable> iterator, IActionAtInsert actionAtInsert) {
		if (!iterator.hasNext()){
			if (this.value == null){
				value = actionAtInsert.actionAtKeyNotFound();
			}
			else{
				value = actionAtInsert.actionAtKeyFound(value);
			}
			return new TrieReference(true, value, this);
		}
		else {
			Comparable nextPartialKey = iterator.next();
			ITrieNode next = outgoingEdgeMap.get(nextPartialKey);
			if (next == null){
				outgoingEdgeMap.put(nextPartialKey, new TrieNode(this.mapFactory, this, nextPartialKey));
				next = outgoingEdgeMap.get(nextPartialKey);
			}
			return next.recursivInsert(iterator, actionAtInsert);
		}
	}
	@Override
	public ITrieReference recursivInsert(String s, IActionAtInsert actionAtInsert) {
		if(s.length() == 0){
			if (this.value == null){
				value = actionAtInsert.actionAtKeyNotFound();
			}
			else{
				value = actionAtInsert.actionAtKeyFound(value);
			}
			return new TrieReference(true, value, this);
		}
		else{
			Comparable nextPartialKey = s.charAt(0);
			ITrieNode next = outgoingEdgeMap.get(nextPartialKey);
			if (next == null){
				outgoingEdgeMap.put(nextPartialKey, new TrieNode(this.mapFactory, this, nextPartialKey));
				next = outgoingEdgeMap.get(nextPartialKey);	
			}
			return next.recursivInsert(s.substring(1), actionAtInsert);
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		if (this.value != null){
			sb.append(this.hashCode() + " [label=\"" + this.value + "\"];");
		}
		else{
			sb.append(this.hashCode() + " [label=\"\"];");
		}
		for (ITrieNode node : outgoingEdgeMap.values()){
			sb.append(this.hashCode() + " -> " + node.hashCode() + "[label = \"  " + node.getIncomingEdge() + "\"];\n");
			sb.append(node.toString());
		}
		return sb.toString();
	}
	@Override
	public Comparable getIncomingEdge() {
		return this.incomingPartialKey;
	}

}
