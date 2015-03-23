package triePackage;

import java.util.Iterator;
import actionsPackage.IActionsAtInsert;

public interface ITrie {
	
	public void insert(Iterator iterator, IActionsAtInsert actionAtInsert);
	public void insert(String string, IActionsAtInsert actionAtInsert);

}
