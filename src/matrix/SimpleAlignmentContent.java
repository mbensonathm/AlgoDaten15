package matrix;

public class SimpleAlignmentContent implements IAlignmentContent {

	private final double value;
	private final Direction dir;
	
	public SimpleAlignmentContent(double v, Direction d) {
		this.value = v;
		this.dir = d;
	}
	
	@Override
	public double getValue() {
		return this.value;
	}

	@Override
	public Direction getDirection() {
		return this.dir;
	}

}
