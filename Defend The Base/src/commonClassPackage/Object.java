package commonClassPackage;

public class Object {
	public String color;
	public int rowPos;
	public int colPos;
    public String objRepresentation;
    Object(String color, int rowPos, int colPos) {
    	this.color = color;
    	this.rowPos = rowPos;
    	this.colPos = colPos;
    	if(this.color.equals("blue")) {
    		this.objRepresentation = "[]";
    	}
    	else {
    		this.objRepresentation = "{}";
    	}
    }
}
