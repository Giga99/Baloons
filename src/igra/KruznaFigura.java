package igra;

import java.awt.Color;

public abstract class KruznaFigura extends Krug {
	private Vektor brzina;
	private Scena scena;

	public KruznaFigura(Vektor centar, Color boja, double precnik, Vektor brzina, Scena scena) {
		super(centar, boja, precnik);
		this.brzina = brzina;
		this.scena = scena;
	}
	
	public Scena getScena() {
		return scena;
	}
	
	public Vektor getBrzina() {
		return brzina;
	}
	
	public void protekaoPeriod() {
		getCentar().saberi(brzina);
		
		if(getCentar().getX() - getPrecnik() / 2 >= scena.getWidth()
				|| getCentar().getY() - getPrecnik() / 2 >= scena.getHeight()) {
			scena.izbaciSaScene(this);
		}
	}
	
	public abstract void obavesti(KruznaFigura figura);
}
