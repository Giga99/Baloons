package igra;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends KruznaFigura {

	public Igrac(Vektor centar, double precnik, Vektor brzina, Scena scena) {
		super(centar, Color.GREEN, precnik, brzina, scena);
	}
	
	@Override
	public void protekaoPeriod() {

	}
	
	@Override
	public void iscrtaj(Scena scena) {
		super.iscrtaj(scena);
		Graphics graphics = getScena().getGraphics();
		
		int x = (int) (getCentar().getX() - (getPrecnik() / 4));
		int y = (int) (getCentar().getY() - (getPrecnik() / 4));
		int precnikInt = (int) (getPrecnik() / 2);
		
		graphics.setColor(Color.BLUE);
		graphics.fillOval(x, y, precnikInt, precnikInt);
	}
	
	public void pomeri(double pomeraj) {
		if((getCentar().getX() + pomeraj - getPrecnik()) >= 0 && (getCentar().getX() + pomeraj + getPrecnik()) <= getScena().getWidth()) 
			getCentar().saberi(new Vektor(pomeraj, 0));
	}
	
	public void obavesti(KruznaFigura figura) {
		if(figura instanceof Balon) getScena().zaustavi();
	}
}
