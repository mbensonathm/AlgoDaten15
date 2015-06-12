package aligment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PushbackReader;

import output.*;
import token.IToken;
import token.ITokenSequence;
import lexer.*;

public class AlignmentControl {
	private PushbackReader readerOriginal;
	private PushbackReader readerSuspect;
	private ITokenSequence seqOriginal;
	private ITokenSequence seqSuspect;
	private String output = "";
	private ILexer lexer;

	public AlignmentControl(PushbackReader o, PushbackReader s, ITokenSequence tko, ITokenSequence tks) {
		this.readerOriginal = o;
		this.readerSuspect = s;
		this.seqOriginal = tko;
		this.seqSuspect = tks;
		this.lexer = new FilterLexer(new AdvancedLexer(new SimpleDFA()));
	}
	
	public void run() throws FileNotFoundException, IOException{		
		// Lexer stuff
		lexer.setPushbackReader(this.readerOriginal);
		int scanResultOriginal = scanText(seqOriginal);
		if (scanResultOriginal == -1){
			output += HTML_Generator.divTags("First (original) reading loop finished.");
			lexer.setPushbackReader(this.readerSuspect);
			int scanResultSuspect = scanText(seqSuspect);
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
		OutputFileGenerator.renderImage(lexer.toString());
		OutputFileGenerator.renderHTML(HTML_Generator.createDoc("Lexer", output));
	}
	
	/**
	 * Loops through text. Classifies words and saves to Tries.
	 * @param r
	 * @param auto
	 * @return
	 * @throws IOException
	 */
	private int scanText(ITokenSequence tk) throws IOException{
		int callCounter = 1;
		IToken token = lexer.getNextToken();
		tk.add(token);
		generateHTMLForEntry(lexer.getOutput(), callCounter);
		while(token.getClassCode() > 0){
			System.out.println("Gelesen: " + token);
			callCounter++;
			token = lexer.getNextToken();
			tk.add(token);
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
	
	public ILexer getLexer(){
		return this.lexer;
	}
}
