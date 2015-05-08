package token;

import triePackage.ITrieReference;

public class TokenTwo implements IToken {

	private int classCode;
	private int relativeCode;

	public TokenTwo(ITrieReference ref, int classCode){
		this.classCode = classCode;
		if (ref != null){
			this.relativeCode = (Integer) ref.getValue();
		}
	}
	
	@Override
	public int getClassCode() {
		return this.classCode;
	}

	@Override
	public int getRelativeCode() {
		return this.relativeCode;
	}
	
	public String toString(){
		return "Class: " + getClassCode() + " Relative: " + getRelativeCode();
	}
}
