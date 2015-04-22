package token;

public interface IToken {
	public static int IDENTIFIER = 1;
	public static int INTCONS = 2;
	public static int DATE = 3;
	public static int PMARK = 4;
	
	int getClassCode();
	int getRelativeCode();
}
