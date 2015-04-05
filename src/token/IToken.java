package token;

public interface IToken {
	public static int IDENTIFIER = new Integer(1);
	public static int INTCONS = new Integer(2);
	public static int DATE = new Integer(3);
	public static int PMARK = new Integer(4);
	
	int getClassCode();
	int getRelativeCode();
}
