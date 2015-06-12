package scoring;

import token.IToken;

public class SimpleScoring implements IScoring {

	@Override
	public double getScore(IToken tk1, IToken tk2) {
		return tk1.getClassCode() == tk2.getClassCode() 
				&& tk1.getRelativeCode() == tk2.getRelativeCode() ? 1.0 : 0.0;
	}

	@Override
	public double getGapScore() {
		return 1.0;
	}
	
	public boolean isPerfect(double s){
		return Math.abs(s-1) <= 0.001 ? true : false;
	}
	
	public boolean isMismatch(double s){
		return Math.abs(s) <= 0.001 ? true : false;
	}
	
	public boolean isNearMatch(double s){
		return s < 1.0 && s > 0.0 ? true : false;
	}

}
