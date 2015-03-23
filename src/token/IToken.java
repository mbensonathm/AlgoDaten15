package token;

public interface IToken {
	public static int IDENTIFIER = new Integer(null);
	public static int INTCONS = new Integer(null);
	public static int DATE = new Integer(null);
	public static int PMARK = new Integer(null);
	
	int getClassCode();
	int getRelativeCode();
}
