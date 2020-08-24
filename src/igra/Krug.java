package igra;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Krug {
	private Vektor centar;
	private Color boja;
	private double precnik;
	
	public Krug(Vektor centar, Color boja, double precnik) {
		super();
		this.centar = centar;
		this.boja = boja;
		this.precnik = precnik;
	}
	
	public Vektor getCentar() {
		return centar;
	}
	
	public double getPrecnik() {
		return precnik;
	}
	
	public boolean preklapajuSe(Krug krug) {
		double xDif = Math.pow(krug.centar.getX() - centar.getX(), 2);
		double yDif = Math.pow(krug.centar.getY() - centar.getY(), 2);
		return Math.sqrt(xDif + yDif) < (krug.precnik + precnik) / 2;
	}

	public void iscrtaj(Scena scena) {
		Graphics graphics = scena.getGraphics();
		
		int x = (int) (centar.getX() - precnik / 2);
		int y = (int) (centar.getY() - precnik / 2);
		int precnikInt = (int) precnik;
		
		graphics.setColor(boja);
		graphics.fillOval(x, y, precnikInt, precnikInt);
	}
}
