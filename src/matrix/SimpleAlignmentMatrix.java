package matrix;

import token.ITokenSequence;

public class SimpleAlignmentMatrix implements IAlignmentMatrix {

	private IAlignmentContent[][] matrix;
	
	public SimpleAlignmentMatrix(ITokenSequence i1, ITokenSequence i2) {
		this.matrix = new IAlignmentContent[i1.length()+1][i2.length()+1];
		for (int i = 0; i <= i1.length()+1; i++){
			for (int j = 0; j <= i2.length()+1; j++){
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
