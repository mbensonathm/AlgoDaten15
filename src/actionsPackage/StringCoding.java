package actionsPackage;

public class StringCoding implements IActionAtInsert{
	
	/* z�hlt */
	private int counter = 0;
	
	public StringCoding(int start) {
		this.counter = start;
	}
	
	public void  setActualValue(int updateValue){
		this.counter = updateValue;
	}
	
	@Override
	public Object actionAtKeyNotFound() {
		return new Integer(counter++);
	}

	@Override
	public Object actionAtKeyFound(Object previous) {
		return previous;
	}

	@Override
	public void setActualValue(Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getActualValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
