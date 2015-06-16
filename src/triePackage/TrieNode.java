package triePackage;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import mapPackage.IMapFactory;
import actionsPackage.IActionAtInsert;

public class TrieNode implements ITrieNode{
	final private TreeMap<Comparable, ITrieNode> outgoingEdgeMap;
	final private ITrieNode parent;
	final private Comparable<Character> incomingPartialKey;
	final private IMapFactory mapFactory;
	private Object value;
	
	public TrieNode(IMapFactory mapFactory, ITrieNode parent, Comparable<Character> incomingPartialKey){
		this.mapFactory = mapFactory;
		this.parent = parent;
		this.incomingPartialKey = incomingPartialKey;
		this.outgoingEdgeMap = (TreeMap<Comparable, ITrieNode>) this.mapFactory.create();
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
			Comparable<Character> nextPartialKey = iterator.next();
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
			Comparable<Character> nextPartialKey = s.charAt(0);
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
			String label = node.getIncomingEdge().toString();
			if (label == "\""){
				label = "\\\"";
			}
			sb.append(this.hashCode() + " -> " + node.hashCode() + "[label = \"  " + label + "\"];\n");
			sb.append(node.toString());
		}
		return sb.toString();
	}
	@Override
	public Comparable<Character> getIncomingEdge() {
		return this.incomingPartialKey;
	}
	
	public boolean isEmpty(){
		return outgoingEdgeMap.isEmpty();
	}

}
