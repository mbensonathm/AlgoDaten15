package actionsPackage;

public class StringCoding implements IActionsAtInsert{
	
	/* zählt */
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
	
	public int getActualValue(){
		return this.counter;
	}

}
