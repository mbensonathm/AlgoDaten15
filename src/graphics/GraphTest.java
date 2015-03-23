package graphics;

import java.io.IOException;

public class GraphTest {

	public static void main(String[] args) {
		DotCodeGenerator gen = new DotCodeGenerator();
		try {
			gen.renderImage(gen.generateDotCode());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
