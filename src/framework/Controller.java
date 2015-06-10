package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;

import matrix.*;
import output.IPresenter;
import output.Presenter;
import aligment.*;
import scoring.*;
import token.*;

public class Controller {

	Controller (String o, String s) throws IOException{
		PushbackReader readerOriginal = initiateReader(o);
		PushbackReader readerSuspect = initiateReader(s);
		ITokenSequence tko = new TokenSequence();
		ITokenSequence tks = new TokenSequence();
		AlignmentControl alCtrl = new AlignmentControl(readerOriginal, readerSuspect, tko, tks);
		alCtrl.run();
		ISelector selector = new SimpleSelector(tko, tks);
		IScoring scoring = new SimpleScoring();
		// Aligner
		IAlignmentMatrix matrix = new SimpleAlignmentMatrix(tko, tks);
		IPresenter presenter = new Presenter(tko, tks, alCtrl.getLexer(), 
											matrix, selector.getRegion(), scoring);
		
		System.out.println(presenter.backward());
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
