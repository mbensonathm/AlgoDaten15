package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;

import Aligment.AlignmentControl;
import token.ITokenSequence;
import token.TokenSequence;

public class Controller {

	Controller (String o, String s) throws IOException{
		PushbackReader readerOriginal = initiateReader(o);
		PushbackReader readerSuspect = initiateReader(s);
		ITokenSequence tko = new TokenSequence();
		ITokenSequence tks = new TokenSequence();
		AlignmentControl alCtrl = new AlignmentControl(readerOriginal, readerSuspect, tko, tks);
		alCtrl.run();
		// Selector
		// Scoring
		// Aligner
		// Presenter
	}
	
	/**
	 * Returns a Reader with given string as content
	 * @param text
	 * @return
	 * @throws FileNotFoundException
	 */
	private PushbackReader initiateReader(String text) throws FileNotFoundException{
		InputStream inStream = new FileInputStream(text);
		InputStreamReader inReader = new InputStreamReader(inStream);
		return new PushbackReader(inReader, 1024);
	}
}
