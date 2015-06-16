package output;

import aligment.IRegion;
import lexer.ILexer;
import matrix.IAlignmentMatrix;
import scoring.IScoring;
import scoring.SimpleScoring;
import token.IToken;
import token.ITokenSequence;

public class Presenter implements IPresenter {
	private ITokenSequence tko;
	private ITokenSequence tks;
	private ILexer lexer;
	private IAlignmentMatrix matrix;
	private IRegion region;
	private SimpleScoring score;
	private double finalScore;
	

	public Presenter(ITokenSequence tko, ITokenSequence tks, ILexer lexer,
			IAlignmentMatrix matrix, IRegion region, IScoring score) {
		this.tko = tko;
		this.tks = tks;
		this.lexer = lexer;
		this.matrix = matrix;
		this.region = region;
		this.score = (SimpleScoring) score;
	}

	@Override
	public String backward() {
		String o1 = "";
		String con = "";
		String o2 = "";
		int i = tko.length();
		int j = tks.length();
		while (i > 0 || j> 0){
			switch (matrix.get(i, j).getDirection()){
			case DIA:
				// Get Tokens
				String t1 = tko.getToken(i-1).toString();
				String t2 = tks.getToken(j-1).toString();
				// Normalize strings' length
				String[] normalized = normalizeLength(t1, t2);
				t1 = normalized[0];
				t2 = normalized[1];
				// Prepend token-strings to output
				o1 = t1 + " " + o1;
				o2 = t2 + " " + o2;
				// Determine consensus
				if (score.isPerfect(score.getScore(tko.getToken(i-1), tks.getToken(j-1)))){
					con = t1 + " " + con;
				}
				if (score.isMismatch(score.getScore(tko.getToken(i-1), tks.getToken(j-1)))){
					String filler = "";
					for (int k = 0; k < Math.max(t1.length(), t2.length()); k++){
						filler += "+";
					}
					con = filler + " " + con;
				}
				i = i-1;
				j = j-1;
				break;
			case HORIZ:
				t2 = tks.getToken(j-1).toString();
				o2 = t2 + " " + o2;
				String filler = "";
				for (int k = 0; k < t2.length(); k++){
					filler += "-";
				}
				o1 = filler + " " + o1;
				con = filler + " " + con;
				j = j-1;
				break;
			case VERT:
				t1 = tko.getToken(i-1).toString();
				o1 = t1 + " " + o1;
				filler = "";
				for (int k = 0; k < t1.length(); k++){
					filler += "-";
				}
				o2 = filler + " " + o2;
				con = filler + " " + con;
				i = i-1;
				break;
			default:
				break;
			}
		}
		return o1.toString() + "<br/>" + con.toString() + "<br />" + o2.toString();
	}

	@Override
	public String[] threeColumnOutput() {
		String[] s = new String[3];
		String o1 = "";
		String con = "";
		String o2 = "";
		int i = tko.length();
		int j = tks.length();
		this.finalScore = matrix.get(i, j).getValue();
		while(i > 0 || j > 0){
			switch (matrix.get(i, j).getDirection()){
			case DIA:
				// Get tokens and decode to clear text
				String token1 = this.lexer.decode(tko.getToken(i-1));
				String token2 = this.lexer.decode(tks.getToken(j-1));
				// Normalize strings
				String[] normalized = normalizeLength(token1, token2);
				token1 = normalized[0];
				token2 = normalized[1];
				// Prepend output with clear text
				o1 = token1 + " " + o1;
				o2 = token2 + " " + o2;
				// Determine consensus
				if (score.isPerfect(score.getScore(tko.getToken(i-1), tks.getToken(j-1)))){
					con = token1 + " " + con;
				}
				if (score.isMismatch(score.getScore(tko.getToken(i-1), tks.getToken(j-1)))){
					String filler = "";
					for (int k = 0; k < Math.max(token1.length(), token2.length()); k++){
						filler += "+";
					}
					con = filler + " " + con;
				}
				i = i-1;
				j = j-1;
				break;
			case HORIZ:
				token2 = this.lexer.decode(tks.getToken(j-1));
				o2 = token2 + " " + o2;
				String filler = "";
				for (int k = 0; k < token2.length(); k++){
					filler += "-";
				}
				o1 = filler + " " + o1;
				con = filler + " " + con;
				j = j-1;
				break;
			case VERT:
				token1 = this.lexer.decode(tko.getToken(i-1));
				o1 = token1 + " " + o1;
				filler = "";
				for (int k = 0; k < token1.length(); k++){
					filler += "-";
				}
				o2 = filler + " " + o2;
				con = filler + " " + con;
				i = i-1;
				break;
			default:
				break;
			}
		}
		s[0] = o1;
		s[1] = con;
		s[2] = o2;
		return s;
	}
	
	private String[] normalizeLength(String s1, String s2){
		int difference = s1.length() - s2.length();
		if (difference > 0){
			for (int i = 0; i < difference; i++){
				s2 += " ";
			}
		}
		if (difference < 0){
			for (int i = 0; i < -difference; i++){
				s1 += " ";
			}
		}
		return new String[]{s1, s2};
	}
	
	public double getFinalScore(){
		return this.finalScore;
	}
	
	public String matrixToString(){
		String s = "";
		String s1 = "<td></td><td></td>";
		for (int l = 0; l < tks.length(); l++){
			s1 += HTML_Generator.tdTags(lexer.decode(tks.getToken(l)));
		}
		s += HTML_Generator.trTags(s1);
		for (int i = 0; i <= this.tko.length(); i++){
			s1 = "";
			if (i <= tko.length() && i > 0){
				s1 += HTML_Generator.tdTags(lexer.decode(tko.getToken(i-1)));
			}
			else{
				s1 += "<td></td>";
			}
			for (int j = 0; j <= this.tks.length(); j++){
				s1 += HTML_Generator.tdTags(matrix.get(i, j).getValue() + " " + matrix.get(i,j).getDirection());
			}
			s += HTML_Generator.trTags(s1);
		}
		return HTML_Generator.tableTags(s);
	}
}
