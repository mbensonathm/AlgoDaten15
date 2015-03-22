package framework;

public class PlagiCheck {

	public static void main(String[] args) throws Exception{
		if(args.length == 2){
			AlignmentController controller = new AlignmentController(args[0], args[1]);
			controller.run();
		}
		else{
			throw new Exception("Wir erwarten den Namen des Originals und den Namen des Plagiats.");
		}
	}

}
