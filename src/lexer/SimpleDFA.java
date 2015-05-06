package lexer;

import java.util.Set;
import java.util.regex.Pattern;

public class SimpleDFA implements IDFA {
	private final int START_STATE = 0;
	private final int FAILURE_STATE = 100;
	private final int EOF_STATE = 101;
	private final int ID_STATE = ID;
	private final int PM_STATE = PM;
	private final int WS_STATE = WS;
	private final int FIRST_OF_DAY_STATE = 10;
	private final int SECOND_OF_DAY_STATE = 11;
	private final int DAY_STATE = 12;
	private final int FIRST_OF_MONTH_STATE = 13;
	private final int SECOND_OF_MONTH_STATE = 14;
	private final int MONTH_STATE = 15;
	private final int FIRST_OF_YEAR_STATE = 16;
	private final int DATE_STATE = DATE;
	private final int INTCONS_STATE = INTCON;

	@Override
	public int getInitial() {
		return START_STATE;
	}
	
	public int[] codesForTries(){
		return new int[]{ID, PM, WS, DATE, INTCON};
	}

	@Override
	public int trans(int state, int symbol) {
		switch (state) {
		case START_STATE:
			if(symbol == -1){return EOF_STATE;}
			if (Character.isWhitespace(symbol)) {
				return WS_STATE;
			}
			if (Pattern.matches("\\p{Punct}", Character.toString(Character.toChars(symbol)[0]))) {
				return PM_STATE;
			}
			if (Character.isLetter(symbol)) {
				return ID_STATE;
			}
			if (symbol == -1) {
				return EOF_STATE;
			}
			if (Character.isAlphabetic(symbol)) {
				return ID_STATE;
			}
			if (Character.isDigit(symbol)) {
				return FIRST_OF_DAY_STATE;
			} else {
				return FAILURE_STATE;
			}
		case ID_STATE:
			if (Character.isLetter(symbol)) {
				return ID_STATE;
			} else {
				return FAILURE_STATE;
			}
		case PM_STATE:
			return FAILURE_STATE;
		case WS_STATE:
			if (Character.isWhitespace(symbol)) {
				return WS_STATE;
			} else {
				return FAILURE_STATE;
			}
		case FIRST_OF_DAY_STATE:
			if (Character.isDigit(symbol)) {
				return SECOND_OF_DAY_STATE;
			} else {
				return FAILURE_STATE;
			}
		case SECOND_OF_DAY_STATE:
			if (Character.isDigit(symbol)) {
				return INTCONS_STATE;
			}
			if (symbol == 46) {
				return DAY_STATE;
			} else {
				return FAILURE_STATE;
			}
		case DAY_STATE:
			if (Character.isDigit(symbol)) {
				return FIRST_OF_MONTH_STATE;
			} else {
				return FAILURE_STATE;
			}
		case FIRST_OF_MONTH_STATE:
			if (Character.isDigit(symbol)) {
				return SECOND_OF_MONTH_STATE;
			} else {
				return FAILURE_STATE;
			}
		case SECOND_OF_MONTH_STATE:
			if (symbol == 46) {
				return MONTH_STATE;
			} else {
				return FAILURE_STATE;
			}
		case MONTH_STATE:
			if (Character.isDigit(symbol)) {
				return FIRST_OF_YEAR_STATE;
			} else {
				return FAILURE_STATE;
			}
		case FIRST_OF_YEAR_STATE:
			if (Character.isDigit(symbol)) {
				return DATE_STATE;
			} else {
				return FAILURE_STATE;
			}
		default:
			return FAILURE_STATE;
		}
	}

	@Override
	public boolean isFinal(int state) {
		if (state >= 100 || state == 10 || state == 11) {
			return true;
		}
		return false;
	}

	@Override
	public Set<Integer> getTokenClasses(int s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFailure(int state) {
		if (state == FAILURE_STATE) {
			return true;
		}
		return false;
	}

	@Override
	public String stateToString(int state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEndofFile(int state) {
		if (state == 101) {
			return true;
		}
		return false;
	}

	@Override
	public int getFinalState(int state) {
		switch (state){
		case ID_STATE: return ID_STATE;
		case WS_STATE: return WS_STATE;
		case PM_STATE: return PM_STATE;
		case FIRST_OF_DAY_STATE: return INTCONS_STATE;
		case SECOND_OF_DAY_STATE: return INTCONS_STATE;
		case DATE_STATE: return DATE_STATE;
		default: return START_STATE;
		}
	}

}
