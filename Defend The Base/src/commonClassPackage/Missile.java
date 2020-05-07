package commonClassPackage;
public class Missile {
	public int location;
	public static String missileRepresentation = "^ ";
	public int power;
	Missile(Board b, int power) {
		this.location = b.board.length - 1;
		this.power = power;
	}
}
