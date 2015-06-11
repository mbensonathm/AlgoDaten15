package matrix;

import scoring.IScoring;
import token.ITokenSequence;

public class SimpleAlignmentMatrix implements IAlignmentMatrix {

	private IAlignmentContent[][] matrix;
	
	public SimpleAlignmentMatrix(ITokenSequence i1, ITokenSequence i2, IScoring score) {
		this.matrix = new IAlignmentContent[i1.length()+1][i2.length()+1];
		matrix[0][0] = new SimpleAlignmentContent(0, Direction.DIAGONAL_MOVE);
		for (int i = 1; i <= i1.length(); i ++){
			matrix[i][0] = new SimpleAlignmentContent((-i) * score.getGapScore(), Direction.VERTICAL_MOVE);
		}
		for (int j = 1; j <= i2.length(); j++){
			matrix[0][j] = new SimpleAlignmentContent((-j) * score.getGapScore(), Direction.HORIZONTAL_MOVE);
		}
		for (int i = 1; i <= i1.length(); i++){
			for (int j = 1; j <= i2.length(); j++){
				matrix[i][j] = new SimpleAlignmentContent(Double.NEGATIVE_INFINITY, Direction.DIAGONAL_MOVE);
			}
		}
	}
	
	@Override
	public void set(int i, int j, IAlignmentContent c) {
		this.matrix[i][j] = c;
	}

	@Override
	public IAlignmentContent get(int i, int j) {
		return this.matrix[i][j];
	}
}
