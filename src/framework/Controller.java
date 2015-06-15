package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;

import matrix.*;
import output.HTML_Generator;
import output.IPresenter;
import output.OutputFileGenerator;
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
		IAligner aligner = new Aligner(selector.getRegion(), scoring, tko, tks);
		IAlignmentMatrix matrix = aligner.forward();
		
		IPresenter presenter = new Presenter(tko, tks, alCtrl.getLexer(), 
											matrix, selector.getRegion(), scoring);
		
		// HTML for tokens
		String output = alCtrl.getOutput();
		// HTML for Scoring
		output += "<h3 id=\"Scoring\">Scoring</h3>" + scoring.toString();
		// HTML for Matrix
		output += "<h3 id=\"Matrix\">Matrix</h3>" + presenter.matrixToString();
		// HTML for 3 Line
		output += "<h3 id=\"LineOutput\">Three Line Output</h3>" + HTML_Generator.divTags(presenter.backward());
		// HTML for 3 column
		output += HTML_Generator.threeColumns(presenter.threeColumnOutput());
		OutputFileGenerator.renderHTML(HTML_Generator.createDoc("Plagiarism Check Results", output, alCtrl.getOutputImage()));
		
//		System.out.println(presenter.threeColumnOutput()[0]);
//		System.out.println(presenter.threeColumnOutput()[1]);
//		System.out.println(presenter.threeColumnOutput()[2]);
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
