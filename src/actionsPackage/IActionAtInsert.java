package actionsPackage;

public interface IActionAtInsert {

	public Object actionAtKeyNotFound();
	public Object actionAtKeyFound(Object previous);
	public void setActualValue(Object value);
	public Object getActualValue();
	
}
