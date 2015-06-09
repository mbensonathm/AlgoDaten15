package token;

import java.util.ArrayList;

public class TokenSequence implements ITokenSequence {
	private ArrayList<IToken> sequence;
	
	public TokenSequence(){
		this.sequence = new ArrayList<IToken>();
	}

	@Override
	public void add(IToken token) {
		this.sequence.add(token);
	}

	@Override
	public IToken getToken(int i) {
		return this.sequence.get(i);
	}

	@Override
	public int length() {
		return this.sequence.size();
	}

}
