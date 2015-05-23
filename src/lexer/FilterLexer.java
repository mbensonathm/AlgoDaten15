package lexer;

import java.io.IOException;

import token.IClassCodes;
import token.IToken;

public class FilterLexer implements ILexer, IClassCodes {
	
	private ILexer coreLexer;
	
	public FilterLexer(ILexer lexer){
		this.coreLexer = lexer;
	}

	@Override
	public IToken getNextToken() throws IOException {
		IToken token = coreLexer.getNextToken();
		while(token.getClassCode() == IClassCodes.WS){
			token = coreLexer.getNextToken();
		}
		return token;
	}

	@Override
	public String decode(IToken token) {
		return coreLexer.decode(token);
	}

	@Override
	public String getOutput() {
		return coreLexer.getOutput();
	}

	@Override
	public String dictionariesToString() {
		return coreLexer.dictionariesToString();
	}

}
