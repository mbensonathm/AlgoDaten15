package lexer;

import java.util.Set;

public interface IDFA {
	
	int getInitial();
	int trans(int state, int symbol);
	boolean isFinal(int state);
	Set<Integer> getTokenClasses(int s);
	boolean isFailure(int state);
	String stateToString(int state);
	boolean isEndofFile(int state);

}
