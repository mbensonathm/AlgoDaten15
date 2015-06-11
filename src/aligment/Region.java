package aligment;

class Region implements IRegion {
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	Region(int x1, int x2, int y1, int y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;				
	}
	
	public int geti1(){
		return this.x1;
	}
	public int geti2(){
		return this.x2;
	}
	public int getj1(){
		return this.y1;
	}
	public int getj2(){
		return this.y2;
	}
}
