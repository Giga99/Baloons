package igra;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Scena extends Canvas implements Runnable {
	private Igra igra;
	private Thread nit;
	private Igrac igrac;
	private ArrayList<Balon> baloni;

	public Scena(Igra igra) {
		super();
		this.igra = igra;
		
		baloni = new ArrayList<>();
		
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_RIGHT: 
						igrac.pomeri(15);
						break;
					case KeyEvent.VK_LEFT: 
						igrac.pomeri(-15);
						break;
				}
			}
		});
	}
	
	public void pokreni() {
		nit = new Thread(this);
		
		requestFocus();
		
		nit.start();
	}
	
	public void dodajNaScenu(KruznaFigura figura) {
		if(figura instanceof Balon) {
			Balon balon = (Balon) figura;
			baloni.add(balon);
		}
	}
	
	public void izbaciSaScene(KruznaFigura figura) {
		if(figura instanceof Balon) {
			Balon balon = (Balon) figura;
			baloni.remove(balon);
		}
	}
	
	public void zaustavi() {
		if(nit != null) nit.interrupt();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if(igrac == null) igrac = new Igrac(new Vektor(getWidth() / 2, getHeight() - 30), 30, new Vektor(0, 0), this);
		
		for(int i = 0; i < baloni.size(); i++) baloni.get(i).iscrtaj(this);
		
		igrac.iscrtaj(this);
	}
	
	public void dodajBalon(int nivo) {
		int rand = (int) (Math.random()*100);
		
		if(rand <= 10) {
			double x = Math.random()*getWidth();
			Balon balon = new Balon(new Vektor(x, 0), 20, new Vektor(0, 10*nivo), this);
			dodajNaScenu(balon);
		}
	}
	
	public void obavestiPreklapanje() {
		for(int i = 0; i < baloni.size(); i++) {
			if(baloni.get(i).preklapajuSe(igrac)) {
				igrac.obavesti(baloni.get(i));
				baloni.get(i).obavesti(igrac);
				igra.getTimer().zaustavi();
			}
		}
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				repaint();
				
				Thread.sleep(60);
				
				dodajBalon(igra.getNivo());
				repaint();
				
				for(int i = 0; i < baloni.size(); i++) baloni.get(i).protekaoPeriod();
				repaint();
				
				obavestiPreklapanje();
				
				if(igra.krajNivoa()) {
					baloni.clear();
					repaint();
					igra.getTimer().zaustavi();
					Thread.sleep(2000);
					igra.uvecajNivo();
				}
			}
			
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}
