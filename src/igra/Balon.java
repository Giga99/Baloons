package igra;

import java.awt.Color;

public class Balon extends KruznaFigura {

	public Balon(Vektor centar, double precnik, Vektor brzina, Scena scena) {
		super(centar, Color.RED, precnik, brzina, scena);
	}

	@Override
	public void obavesti(KruznaFigura figura) {
		// TODO Auto-generated method stub
		
	}
}
