package actionsPackage;

public interface IActionsAtInsert {

	public Object actionAtKeyNotFound();
	public Object actionAtKeyFound(Object previous);
	public void setActualValue(Object value);
	public Object getActualValue();
	
}
