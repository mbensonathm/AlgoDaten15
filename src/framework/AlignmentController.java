package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
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
		Reader readerOriginal = initiateReader(original);
		
		// Lexer stuff
		ILexer lexer = new FilterLexer(new AdvancedLexer(readerOriginal, new SimpleDFA()));
		int scanResultOriginal = scanText(lexer, null);
		if (scanResultOriginal == -1){
			output += HTML_Generator.divTags("First (original) reading loop finished.");
			int scanResultSuspect = scanText(lexer, initiateReader(suspect));
			if (scanResultSuspect == -1){
				output += HTML_Generator.divTags("Second (suspect) reading loop finished.");
			}
			if (scanResultSuspect == -2){
				output += HTML_Generator.divTags("Error reading the suspect file.");
			}
		}
		if (scanResultOriginal == -2){
			output += HTML_Generator.divTags("Error reading the original file.");
		}
		// Output stuff
		System.out.println("Reading complete.");
//		OutputFileGenerator.renderImage(lexer.toString());
		OutputFileGenerator.renderHTML(HTML_Generator.creatDoc("Lexer", output));
	}
	
	/**
	 * Returns a Reader with given string as content
	 * @param text
	 * @return
	 * @throws FileNotFoundException
	 */
	private Reader initiateReader(String text) throws FileNotFoundException{
		InputStream inStream = new FileInputStream(text);
		return new InputStreamReader(inStream);
	}
	
	/**
	 * Loops through text. Classifies words and saves to Tries.
	 * @param r
	 * @param auto
	 * @return
	 * @throws IOException
	 */
	private int scanText(ILexer lexer, Reader r) throws IOException{
		if (r != null){
			lexer.setPushbackReader(r);
		}
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
		return token.getClassCode();
	}
	
	private void generateHTMLForEntry(String output, int ctr){
		String html = HTML_Generator.divTags("GetNextToken called (" + ctr +")"
											+ output);
		this.output += html;
	}
}
