package lexer;

import java.io.IOException;
import java.io.Reader;
import java.io.PushbackReader;
import java.util.HashMap;

import mapPackage.TreeMapFactory;
import token.Token;
import token.TokenTwo;
import actionsPackage.IActionAtInsert;
import actionsPackage.StringCoding;
import token.IToken;
import triePackage.ITrie;
import triePackage.Trie;
import triePackage.TrieReference;

public class BaseLexer implements ILexer {
	private PushbackReader reader;
	private IDFA auto;
//	private ITrie trie;
	private HashMap<Integer, ITrie> tries;
//	private IActionAtInsert action = new StringCoding(4711);
	private TreeMapFactory mapFactory = new TreeMapFactory();
	
	public BaseLexer(Reader r, IDFA auto){
		this.reader = new PushbackReader(r, 1024);
		this.auto = auto;
		initiateTries(auto.codesForTries());
	}
	
	private void initiateTries(int[] codes) {
		this.tries = new HashMap<>();
		for (int i : codes){
			this.tries.put(i, new Trie(mapFactory, new StringCoding(4711)));
		}
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
				lastFinalPosition = position - 1;
				lastFinalState = auto.getFinalState(state);
			}
			int nextChar = reader.read();
			position++;
			if (nextChar > -1){
				tokenBuffer.append(Character.toChars(nextChar)[0]);
			}
			state = auto.trans(state, nextChar);
		}
		// Save final state token to correct trie
		if (lastFinalPosition > -1){
			if (tokenBuffer.length() > lastFinalPosition + 1){
				char[] chars = tokenBuffer.substring(lastFinalPosition + 1).toCharArray();
				reader.unread(chars);;
			}
			ITrie trie = tries.get(lastFinalState);
			if (trie != null){
				return new TokenTwo(trie.insert(tokenBuffer.substring(0, lastFinalPosition + 1)), lastFinalState);
			} else{
				// log error
			}
		}
		if (lastFinalPosition == -1){
			return new Token(new TrieReference(false, null, null));
		}
		if (auto.isEndofFile(state)){
			return null;
//			return new Token(new TrieReference(false, null, null));
		}
		return null;
	}

	@Override
	public String decode(IToken token) {
		return token.toString();
	}
	
	public String toString(){
		String toReturn = "digraph G { \n node [shape = circle, style = filled, color=red];\n";
		for(ITrie trie : tries.values()){
			toReturn += (trie.toString() + "\n");
		}
		toReturn += "}";
		return toReturn;
	}
	

}
