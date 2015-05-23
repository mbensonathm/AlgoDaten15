package triePackage;

import java.util.Iterator;
import java.util.TreeMap;

import token.IToken;
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
	
	TreeMap<Integer, String> getClearText();

}
