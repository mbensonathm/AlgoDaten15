package lexer;

import java.io.IOException;
import java.io.Reader;
import java.io.PushbackReader;
import java.util.HashMap;

import output.HTML_Generator;
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
	private String output;
	
	public BaseLexer(Reader r, IDFA auto){
		this.reader = new PushbackReader(r, 1024);
		this.auto = auto;
		initiateTries(auto.codesForTries());
	}
	
	@Override
	public IToken getNextToken() throws IOException {
		initOutput();
		// Set up reader loop
		int currentPosition = 0;
		StringBuffer tokenBuffer = new StringBuffer();
		int lastFinalPosition = -1;
		int lastFinalState = -1;
		int state = auto.getInitial();
		int loopCount = 0;
		while(!auto.isFailure(state) && !auto.isEndofFile(state)){
			output += HTML_Generator.trTags(HTML_Generator.tdTags("+" + loopCount) +
											HTML_Generator.tdTags("" + currentPosition) +
											HTML_Generator.tdTags("" + state + " " + auto.stateToString(state)) +
											HTML_Generator.tdTags("" + lastFinalPosition) +
											HTML_Generator.tdTags("" + lastFinalState + " " + auto.stateToString(lastFinalState)) +
											HTML_Generator.tdTags("" + tokenBuffer.length()) +
											HTML_Generator.tdTags(">>" + tokenBuffer.toString() + "<<"));
			loopCount++;
			if (auto.isFinal(state)){
				lastFinalPosition = currentPosition;
				lastFinalState = state;
			}
			int nextChar = reader.read();
			currentPosition++;
			if (nextChar > -1){
				tokenBuffer.append(Character.toChars(nextChar)[0]);
			}

			state = auto.trans(state, nextChar);
		}
		// End read process
		output += HTML_Generator.trTags(HTML_Generator.tdTags("*" + loopCount) +
				HTML_Generator.tdTags("" + currentPosition) +
				HTML_Generator.tdTags("" + state + " " + auto.stateToString(state)) +
				HTML_Generator.tdTags("" + lastFinalPosition) +
				HTML_Generator.tdTags("" + lastFinalState + " " + auto.stateToString(lastFinalState)) +
				HTML_Generator.tdTags("" + tokenBuffer.length()) +
				HTML_Generator.tdTags(">>" + tokenBuffer.toString() + "<<"));
		// Save final state token to correct trie
		if (lastFinalPosition > -1){
			ITrie trie = tries.get(auto.getFinalState(lastFinalState));
			if (tokenBuffer.length() > lastFinalPosition){
				char[] chars = tokenBuffer.substring(lastFinalPosition).toCharArray();
				reader.unread(chars);
				if (trie != null){
					return new TokenTwo(trie.insert(tokenBuffer.substring(0, lastFinalPosition + 1)), lastFinalState);
				}
			} else{
				if (trie != null){
					return new TokenTwo(trie.insert(tokenBuffer.substring(0, lastFinalPosition)), lastFinalState);
				}
			}			
		}
		if (lastFinalPosition == -1){
			return null;
//			return new Token(new TrieReference(false, -1, null));
		}
		if (auto.isEndofFile(state)){
			return null;
//			return new Token(new TrieReference(false, -1, null));
		}
		return null;
	}

	@Override
	public String decode(IToken token) {
		return token.toString();
	}
	
	@Override
	public String getOutput(){
		return this.output;
	}
	
	public String toString(){
		String toReturn = "digraph G { \n node [shape = circle, style = filled, color=red];\n";
		for(ITrie trie : tries.values()){
			toReturn += (trie.toString() + "\n");
		}
		toReturn += "}";
		return toReturn;
	}
	
	private void initiateTries(int[] codes) {
		this.tries = new HashMap<>();
		for (int i : codes){
			this.tries.put(i, new Trie(mapFactory, new StringCoding(4711)));
		}
	}
	
	private void initOutput(){
		this.output =  HTML_Generator.trTags(HTML_Generator.tdTags("Cnt") +
				HTML_Generator.tdTags("P0") +
				HTML_Generator.tdTags("Current DFA State") +
				HTML_Generator.tdTags("FP") +
				HTML_Generator.tdTags("Last Final State") +
				HTML_Generator.tdTags("L") +
				HTML_Generator.tdTags("Buffer=Tokenstring|Remaining"));
	}
}
