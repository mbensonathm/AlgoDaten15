package token;

import triePackage.ITrieReference;

public class Token implements IToken{
	
	private int classCode;
	private int relativeCode;

	public Token(ITrieReference ref){
		// TODO: NEEDS TO BE CHANGED FOR OTHER CLASS TYPES
		this.classCode = IToken.ID;
		this.relativeCode = (Integer) ref.getValue();
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
		return "(Class: " + getClassCode() + " Relative: " + getRelativeCode() + ")";
	}
	
}
