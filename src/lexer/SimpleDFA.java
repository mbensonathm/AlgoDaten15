package lexer;

import java.util.Set;
import java.util.function.IntConsumer;
import java.util.regex.Pattern;

import token.IClassCodes;

public class SimpleDFA implements IDFA {
	private final int START_STATE = 0;
	private final int FAILURE_STATE = 100;
	private final int EOF_STATE = 101;
	private final int ID_STATE = 150;
	private final int PM_STATE = 151;
	private final int WS_STATE = 152;
	private final int FIRST_OF_DAY_STATE = 10;
	private final int SECOND_OF_DAY_STATE = 11;
	private final int DAY_STATE = 12;
	private final int FIRST_OF_MONTH_STATE = 13;
	private final int SECOND_OF_MONTH_STATE = 14;
	private final int MONTH_STATE = 15;
	private final int FIRST_OF_YEAR_STATE = 16;
	private final int DATE_STATE = 153;
	private final int INTCONS_STATE = 154;
	private final int DEFAULT_STATE = 155;

	@Override
	public int getInitial() {
		return START_STATE;
	}
	
	public int[] codesForTries(){
		return new int[]{ID, PM, WS, DATE, INTCON, DEFAULT};
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
//			if (symbol == 33 || symbol == 46 || symbol == 44 || symbol == 59 || symbol == 63){
//				return PM_STATE;
//			}
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
				return DEFAULT_STATE;
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
		case INTCONS_STATE: {
			if (Character.isDigit(symbol)) {
				return INTCONS_STATE;
			} else {
				return FAILURE_STATE;
			}
		}
		case DEFAULT_STATE: {
			if (Pattern.matches("\\p{Punct}", Character.toString(Character.toChars(symbol)[0]))){
				return DEFAULT_STATE;
			}
			if (Character.isWhitespace(symbol)){
				return DEFAULT_STATE;
			} else {
				return FAILURE_STATE;
			}
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
		switch (state) {
		case (START_STATE): return "START_STATE";
		case (FAILURE_STATE): return "FAILURE_STATE";
		case (EOF_STATE): return "EOF_STATE";
		case (ID_STATE): return "ID_STATE";
		case (PM_STATE): return "PM_STATE";
		case (WS_STATE): return "WS_STATE";
		case (FIRST_OF_DAY_STATE): return "FIRST_OF_DAY_STATE"; 
		case (SECOND_OF_DAY_STATE): return "SECOND_OF_DAY_STATE"; 
		case (DAY_STATE): return "DAY_STATE"; 
		case (FIRST_OF_MONTH_STATE): return "FIRST_OF_MONTH_STATE"; 
		case (SECOND_OF_MONTH_STATE): return "SECOND_OF_MONTH_STATE"; 
		case (MONTH_STATE): return "MONTH_STATE"; 
		case (FIRST_OF_YEAR_STATE): return "FIRST_OF_YEAR_STATE"; 
		case (INTCONS_STATE): return "INTCONS_STATE";
		case (DATE_STATE): return "DATE_STATE"; 
		case DEFAULT_STATE: return "DEFAULT_STATE";
		default: return "--";
		}
	}

	@Override
	public boolean isEndofFile(int state) {
		if (state == EOF_STATE) {
			return true;
		}
		return false;
	}

	@Override
	public int getClassFromFinalState(int state) {
		switch (state){
		case ID_STATE: return IClassCodes.ID;
		case WS_STATE: return IClassCodes.WS;
		case PM_STATE: return IClassCodes.PM;
		case FIRST_OF_DAY_STATE: return IClassCodes.INTCON;
		case SECOND_OF_DAY_STATE: return IClassCodes.INTCON;
		case DATE_STATE: return IClassCodes.DATE;
		case INTCONS_STATE: return IClassCodes.INTCON;
		default: return -1;
		}
	}

}
