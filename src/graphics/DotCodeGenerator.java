package graphics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DotCodeGenerator {
	
	public String generateDotCode(){
		return "digraph G { node [shape = circle, style = filled, color=black];"
				+ "Root -> a1 [label = \"a\"];"
				+ "a1 -> l [label = \"l\"];"
				+ "l -> p [label = \"p\"];"
				+ "l -> f [label = \"f\"];"
				+ "f -> o [label = \"o\"];"
				+ "o -> n [label = \"n\"];"
				+ "n -> s [label = \"s\"];"
				+ "p -> h [label = \"h\"];h -> a2 [label = \"a\"];}";
	}
	
	public void renderImage(String dotCode) throws IOException{
		// Write code to text file
		File temp = File.createTempFile("dotcode", ".txt");
		FileWriter writer = new FileWriter(temp);
		writer.write(dotCode);
		writer.close();
		String filePathAndName = temp.getAbsolutePath();
		String filePath = filePathAndName.substring(0,filePathAndName.lastIndexOf(File.separator));
		String workingDir = System.getProperty("user.dir");
		// Call Graphviz program with text file
		Process p = Runtime.getRuntime().exec("\"" + workingDir + "\\src\\graphics\\bin\\dot.exe\" -o \"" + filePath + "\\graph.gif\" -Tgif " + "\""+filePathAndName + "\"");
		Process p2 = Runtime.getRuntime().exec("explorer \"" + filePath + "\\graph.gif\"");
	}

}
