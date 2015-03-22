package lexer;

import java.io.IOException;

import token.IToken;

public interface ILexer {
	IToken getNextToken() throws IOException;
	String decode(IToken token);
}
