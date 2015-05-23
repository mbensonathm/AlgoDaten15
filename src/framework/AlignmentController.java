package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import output.*;
import token.IToken;
import lexer.*;

public class AlignmentController {
	final private String original;
	final private String suspect;
	private String output = "";
	/**
	 * @param original
	 * @param suspect
	 */
	public AlignmentController(String original, String suspect) {
		this.original = original;
		this.suspect = suspect;
	}
	
	public void run() throws FileNotFoundException, IOException{
		// Reader stuff
		InputStream inStreamOriginal = new FileInputStream(original);
		Reader readerOriginal = new InputStreamReader(inStreamOriginal);
//		BufferedReader inputOriginal = new BufferedReader(readerOriginal);
//		ILexer lexer = new SimpleLexer(inputOriginal);
		
		// Lexer stuff
		ILexer lexer = new FilterLexer(new AdvancedLexer(readerOriginal, new SimpleDFA()));
		int callCounter = 1;
		IToken token = lexer.getNextToken();
		generateHTMLForEntry(lexer.getOutput(), callCounter);
		while(token.getClassCode() > 0){
			System.out.println("Gelesen: " + token);
			callCounter++;
			token = lexer.getNextToken();
			generateHTMLForEntry(lexer.getOutput(), callCounter);
		}
		System.out.println(lexer.dictionariesToString());
		if (token.getClassCode() == -1){
			output += HTML_Generator.divTags("First (original) reading loop finished.");
		}
		if (token.getClassCode() == -2){
			output += HTML_Generator.divTags("Error reading the file.");
		}
		// Output stuff
		System.out.println("Lesen fertig.");
//		OutputFileGenerator.renderImage(lexer.toString());
	
		OutputFileGenerator.renderHTML(HTML_Generator.creatDoc("Lexer", output));
	}
	
	private void generateHTMLForEntry(String output, int ctr){
		String html = HTML_Generator.divTags("GetNextToken called (" + ctr +")"
											+ output);
		this.output += html;
	}
}
