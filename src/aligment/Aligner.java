package aligment;

import scoring.IScoring;
import token.ITokenSequence;
import matrix.Direction;
import matrix.IAlignmentContent;
import matrix.IAlignmentMatrix;
import matrix.SimpleAlignmentContent;
import matrix.SimpleAlignmentMatrix;

public class Aligner implements IAligner {
	private IRegion region;
	private IScoring scoring;
	private ITokenSequence tko;
	private ITokenSequence tks;

	public Aligner (IRegion region, IScoring scoring, ITokenSequence tko, ITokenSequence tks){
		this.region = region;
		this.scoring = scoring;
		this.tko = tko;
		this.tks = tks;
	}
	@Override
	public IAlignmentMatrix forward() {
		IAlignmentMatrix matrix = new SimpleAlignmentMatrix(tko, tks, scoring);
		int m = tko.length();
		int n = tks.length();
		int i = 1;
		int j = 1;
		
		while (i <= m && j <= n){
			double vertScore = matrix.get(i-1, j).getValue();
			double horizScore = matrix.get(i, j-1).getValue();
			double diaScore = matrix.get(i-1, j-1).getValue() + scoring.getScore(tko.getToken(i-1), tks.getToken(j-1));
			IAlignmentContent content = new SimpleAlignmentContent(vertScore, Direction.VERTICAL_MOVE);
			if (horizScore > content.getValue()){
				content = new SimpleAlignmentContent(horizScore, Direction.HORIZONTAL_MOVE);
			}
			if (diaScore > content.getValue()){
				content = new SimpleAlignmentContent(diaScore, Direction.DIAGONAL_MOVE);
			}
			i++;
			j++;
		}
		return matrix;
	}

}
