package matrix;

public interface IAlignmentMatrix {
	void set(int i, int j, IAlignmentContent c);
	IAlignmentContent get(int i, int j);
}
