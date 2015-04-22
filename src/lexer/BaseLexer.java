package lexer;

import java.io.IOException;
import java.io.Reader;
import java.io.PushbackReader;

import token.Token;
import actionsPackage.IActionAtInsert;
import token.IToken;
import triePackage.ITrie;
import triePackage.TrieReference;

public class BaseLexer implements ILexer {
	private PushbackReader reader;
	private IDFA auto;
	private ITrie trie;
	private IActionAtInsert action;
	
	public BaseLexer(Reader r, IDFA auto){
		this.reader = new PushbackReader(r);
		this.auto = auto;
	}
	@Override
	public IToken getNextToken() throws IOException {
		int position = 0;
		StringBuffer tokenBuffer = new StringBuffer();
		int lastFinalPosition = 0;
		int lastFinalState = 0;
		int state = auto.getInitial();
		while(!auto.isFailure(state) && !auto.isEndofFile(state)){
			if (auto.isFinal(state)){
				lastFinalPosition = position;
				lastFinalState = state;
			}
			int nextChar = reader.read();
			position++;
			if (nextChar > -1){
				tokenBuffer.append(nextChar);
			}
			state = auto.trans(state, nextChar);
		}
		if (lastFinalPosition > -1){
			//reader.unread();
			return new Token(trie.insert(tokenBuffer.substring(0, lastFinalPosition), action));
		}
		if (lastFinalPosition == -1){
			return new Token(new TrieReference(false, null, null));
		}
		if (auto.isEndofFile(state)){
			return new Token(new TrieReference(false, null, null));
		}
		return null;
	}

	@Override
	public String decode(IToken token) {
		return token.toString();
	}

}
