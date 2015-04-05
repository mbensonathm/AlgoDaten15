package triePackage;

import java.util.Iterator;

import actionsPackage.IActionAtInsert;

/**
 * test
 *
 * @author V
 *
 */
public interface ITrie {

	public ITrieReference insert(Iterator iterator, IActionAtInsert value);
	
	public ITrieReference insert (String string, IActionAtInsert value);

}
