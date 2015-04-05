package mapPackage;

import java.util.HashMap;
import java.util.Map;

import triePackage.ITrieNode;

public class TreeMapFactory implements IMapFactory{

	@Override
	public Map create() {
		return new HashMap<Integer, ITrieNode>();
	}

}
