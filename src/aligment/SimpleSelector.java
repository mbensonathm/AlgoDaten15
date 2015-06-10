package aligment;

import token.ITokenSequence;

public class SimpleSelector implements ISelector {
	private ITokenSequence seqOriginal;
	private ITokenSequence seqSuspect;
	
	public SimpleSelector(ITokenSequence tko, ITokenSequence tks){
		this.seqOriginal = tko;
		this.seqSuspect = tks;
	}
	@Override
	public IRegion getRegion() {
		return new Region(0, this.seqOriginal.length()-1, 
							0, this.seqSuspect.length()-1);
	}

}
