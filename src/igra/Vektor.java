package igra;

public class Vektor implements Cloneable {
	private double x, y;

	public Vektor(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void pomnozi(double vrednost) {
		x *= vrednost;
		y *= vrednost;
	}
	
	public void saberi(Vektor v2) {
		x += v2.x;
		y += v2.y;
	}
	
	@Override
	protected Vektor clone() {
		Vektor vektor = null;
		
		try {
			vektor = (Vektor) super.clone();
			vektor.x = x;
			vektor.y = y;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return vektor;
	}

}
