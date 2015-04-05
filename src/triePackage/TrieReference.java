package triePackage;

public class TrieReference implements ITrieReference{
	
	private boolean found;
	private Object value;
	private ITrieNode trieNode;
	
	public TrieReference(boolean found, Object value, ITrieNode trieNode){
		this.found = found;
		this.value = value;
		this.trieNode = trieNode;
	}

	@Override
	public boolean getFound() {
		return this.found;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

	@Override
	public ITrieNode getNode() {
		return this.trieNode;
	}

}
