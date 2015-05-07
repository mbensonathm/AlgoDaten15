package output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFileGenerator {
		
	public static void renderImage(String dotCode) throws IOException{
		// Write code to text file
		File temp = File.createTempFile("dotcode", ".txt");
		FileWriter writer = new FileWriter(temp);
		writer.write(dotCode);
		writer.close();
		String filePathAndName = temp.getAbsolutePath();
		String filePath = filePathAndName.substring(0,filePathAndName.lastIndexOf(File.separator));
		String workingDir = System.getProperty("user.dir");
		// Call Graphviz program with text file
		Process p = Runtime.getRuntime().exec("\"" + workingDir + "\\src\\output\\bin\\dot.exe\" -o \"" + filePath + "\\graph.gif\" -Tgif " + "\""+filePathAndName + "\"");
		Process p2 = Runtime.getRuntime().exec("explorer \"" + filePath + "\\graph.gif\"");
	}
	public static void renderHTML(String code) throws IOException{
		// Write code to text file
		File temp = File.createTempFile("lexer", ".html");
		FileWriter writer = new FileWriter(temp);
		writer.write(code);
		writer.close();
		String filePathAndName = temp.getAbsolutePath();
		Runtime.getRuntime().exec("explorer \"" + filePathAndName);
	}
}
