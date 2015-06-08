package lexer;

import java.io.IOException;
import java.io.Reader;

import token.IToken;

public interface ILexer {
	IToken getNextToken() throws IOException;
	String decode(IToken token);
	String getOutput();
	String dictionariesToString();
	void setPushbackReader(Reader r);
}
