package lexer;

import java.util.Set;
import token.IClassCodes;

public interface IDFA extends IClassCodes {
	
	int getInitial();
	int trans(int state, int symbol);
	boolean isFinal(int state);
	Set<Integer> getTokenClasses(int s);
	boolean isFailure(int state);
	String stateToString(int state);
	boolean isEndofFile(int state);
	int[] codesForTries();
	public int getClassFromFinalState(int state);
}
