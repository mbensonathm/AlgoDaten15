package matrix;

public interface IMatrix<T> {
	void set(int i, int j, T c);
	T get(int i, int j);
}
