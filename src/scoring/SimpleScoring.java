package scoring;

import token.IToken;

public class SimpleScoring implements IScoring {
	
	private double match = 1.0;
	private double gap = 1.0;

	@Override
	public double getScore(IToken tk1, IToken tk2) {
		return tk1.getClassCode() == tk2.getClassCode() 
				&& tk1.getRelativeCode() == tk2.getRelativeCode() ? getMatchScore() : 0.0;
	}

	@Override
	public double getGapScore() {
		return gap;
	}
	
	public double getMatchScore(){
		return match;
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
	
	public String toString(){
		String s = "";
		s += "Description of Scoring Module:<br/>";
		s += "Score for matches: " + getMatchScore() + "<br/>";
		s += "Score for gaps: " + getGapScore();
		return s;
	}

}
