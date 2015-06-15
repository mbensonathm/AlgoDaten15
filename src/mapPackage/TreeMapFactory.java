package mapPackage;

import java.util.Map;
import java.util.TreeMap;

import triePackage.ITrieNode;

public class TreeMapFactory implements IMapFactory{

	@Override
	public Map<Integer, ITrieNode> create() {
		return new TreeMap<Integer, ITrieNode>();
	}

}
