package token;

public interface ITokenSequence {
	void add(IToken token);
	IToken getToken(int i);
	int length();
}
