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

	public ITrieReference insert(Iterator iterator);
	
	public ITrieReference insert (String string);
	
	public ITrieReference lookup(Iterator iterator);
	
	public ITrieReference lookup(String string);

}
