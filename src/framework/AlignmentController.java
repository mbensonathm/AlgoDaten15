package framework;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import token.IToken;
import lexer.*;

public class AlignmentController {
	final private String original;
	final private String suspect;
	/**
	 * @param original
	 * @param suspect
	 */
	public AlignmentController(String original, String suspect) {
		this.original = original;
		this.suspect = suspect;
	}
	
	public void run() throws FileNotFoundException, IOException{
		InputStream inStreamOriginal = new FileInputStream(original);
		Reader readerOringial = new InputStreamReader(inStreamOriginal);
		BufferedReader inputOriginal = new BufferedReader(readerOringial);
		
		ILexer lexer = new SimpleLexer(inputOriginal);
		IToken token = lexer.getNextToken();
		while(token != null){
			System.out.println("Gelesen: " + token);
			token = lexer.getNextToken();
		}
	}
}
