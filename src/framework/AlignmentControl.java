package framework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PushbackReader;

import output.*;
import token.IToken;
import lexer.*;

public class AlignmentControl {
	private PushbackReader readerOriginal;
	private PushbackReader readerSuspect;
	private String output = "";

	public AlignmentControl(PushbackReader o, PushbackReader s) {
		this.readerOriginal = o;
		this.readerSuspect = s;
	}
	
	public void run() throws FileNotFoundException, IOException{		
		// Lexer stuff
		ILexer lexer = new FilterLexer(new AdvancedLexer(new SimpleDFA()));
		lexer.setPushbackReader(this.readerOriginal);
		int scanResultOriginal = scanText(lexer);
		if (scanResultOriginal == -1){
			output += HTML_Generator.divTags("First (original) reading loop finished.");
			lexer.setPushbackReader(this.readerSuspect);
			int scanResultSuspect = scanText(lexer);
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
	 * Loops through text. Classifies words and saves to Tries.
	 * @param r
	 * @param auto
	 * @return
	 * @throws IOException
	 */
	private int scanText(ILexer lexer) throws IOException{
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
