package matrix;

import output.HTML_Generator;
import scoring.IScoring;
import token.ITokenSequence;

public class SimpleAlignmentMatrix implements IAlignmentMatrix {

	private IAlignmentContent[][] matrix;
	private ITokenSequence i1;
	private ITokenSequence i2;
	
	
	public SimpleAlignmentMatrix(ITokenSequence i1, ITokenSequence i2, IScoring score) {
		this.i1 = i1;
		this.i2 = i2;
		this.matrix = new IAlignmentContent[i1.length()+1][i2.length()+1];
		matrix[0][0] = new SimpleAlignmentContent(0, Direction.DIA);
		for (int i = 1; i <= i1.length(); i ++){
			matrix[i][0] = new SimpleAlignmentContent((-i) * score.getGapScore(), Direction.VERT);
		}
		for (int j = 1; j <= i2.length(); j++){
			matrix[0][j] = new SimpleAlignmentContent((-j) * score.getGapScore(), Direction.HORIZ);
		}
		for (int i = 1; i <= i1.length(); i++){
			for (int j = 1; j <= i2.length(); j++){
				matrix[i][j] = new SimpleAlignmentContent(Double.NEGATIVE_INFINITY, Direction.DIA);
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
	
	public String toString(){
		String s = "";
		for (int i = 0; i <= this.i1.length(); i++){

			String s1 = "";
			for (int j = 0; j <= this.i2.length(); j++){
				s1 += HTML_Generator.tdTags(matrix[i][j].getValue() + " " + matrix[i][j].getDirection());
			}
			s += HTML_Generator.trTags(s1);
		}
		return HTML_Generator.tableTags(s);
	}
}
