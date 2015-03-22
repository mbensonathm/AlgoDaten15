package lexer;

import java.io.IOException;

public interface ILexer {
	IToken getNextToken() throws IOException;
	String decode(IToken token);
}
