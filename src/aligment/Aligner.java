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
		//int m = region.geti2() + 1;
//		int n = region.getj2() + 1;
		//int i = region.geti1() + 1;
//		int j = region.getj1() + 1;
		
		for(int i = region.geti1() + 1; i <= region.geti2() + 1; i++){
			for (int j = region.getj1() + 1; j <= region.getj2() + 1; j++){
				double vertScore = matrix.get(i-1, j).getValue() - scoring.getGapScore();
				double horizScore = matrix.get(i, j-1).getValue() - scoring.getGapScore();
				double diaScore = matrix.get(i-1, j-1).getValue() + scoring.getScore(tko.getToken(i-1), tks.getToken(j-1));
				IAlignmentContent content = new SimpleAlignmentContent(vertScore, Direction.VERT);
				if (horizScore > content.getValue()){
					content = new SimpleAlignmentContent(horizScore, Direction.HORIZ);
				}
				if (diaScore > content.getValue()){
					content = new SimpleAlignmentContent(diaScore, Direction.DIA);
				}
				matrix.set(i, j, content);
			}
		}
		return matrix;
	}

}
