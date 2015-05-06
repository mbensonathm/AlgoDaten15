package lexer;

import java.util.Set;
import token.ITokenCodes;

public interface IDFA extends ITokenCodes {
	
	int getInitial();
	int trans(int state, int symbol);
	boolean isFinal(int state);
	Set<Integer> getTokenClasses(int s);
	boolean isFailure(int state);
	String stateToString(int state);
	boolean isEndofFile(int state);
	int[] codesForTries();
	int getFinalState(int state);
}
