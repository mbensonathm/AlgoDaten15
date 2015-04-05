package lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import actionsPackage.*;
import token.*;
import triePackage.ITrie;
import triePackage.Trie;

public class SimpleLexer implements ILexer{
	final private BufferedReader reader;
	//final private IMapFactory mapFactory = new TreeMapFactory();
	final private IActionAtInsert action = new StringCoding(4711);
	final private ITrie trie;
	private String line;
	private StringTokenizer tk = null;
	
	public SimpleLexer (BufferedReader reader) throws IOException{
		this.reader = reader;
		line = reader.readLine();
		if(line != null) tk = new StringTokenizer(line);
		this.trie = null; //new Trie(mapFactory);
		
	}
	
	public IToken getNextToken() throws IOException{
		//Log.println(Log.URGENT,"--> next token");
		IToken result = null;
		boolean foundToken = false;
		boolean noMoreTokens = false;
		do{
			result = null;
			if(tk != null){
				if(tk.hasMoreTokens()){
					String intermediate = tk.nextToken();
					//Log.println(Log.URGENT, "--- next token:" + intermediate);
					result = (IToken) trie.insert(intermediate, action);
					result = new Token(-1, -1);
					foundToken = true;
				}
				else{
					tk = null;
					line = reader.readLine();
					if(line != null)
						tk = new StringTokenizer(line);
				}
			}
			else
				noMoreTokens = true;
		}
		while(!foundToken && !noMoreTokens);
		//Log.println(Log.URGENT, "<-- result token: " + result);
		return result;
	}
	
	public String decode(IToken token) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("noch nicht implementiert");
	}
	
	public String toString(){
		return "\nResult Trie \n" + trie;
	}
	

}
