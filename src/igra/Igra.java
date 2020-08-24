package igra;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra extends Frame {
	private Scena scena;
	private int nivo = 1;
	private Timer timer;
	private Label nivoLabel = new Label("Nivo: " + nivo);
	private Label vreme = new Label();

	public Igra() {
		super("Baloni");
		setSize(500, 500);
		
		scena = new Scena(this);
		add(scena, BorderLayout.CENTER);
		
		timer = new Timer(vreme);
		timer.start();
		timer.pokreni();
		
		Panel bottom = new Panel();
		bottom.add(new Label("Vreme: "));
		bottom.add(vreme);
		bottom.add(nivoLabel);
		
		add(bottom, BorderLayout.SOUTH);
		
		scena.pokreni();
		scena.requestFocus();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				scena.zaustavi();
				timer.interrupt();
				dispose();
			}
		});
		
		setVisible(true);
		setResizable(false);
	}
	
	public boolean krajNivoa() {
		return timer.getS() == 0;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public void uvecajNivo() {
		nivo++;
		nivoLabel.setText("Nivo: " + nivo);
		if(timer != null) {
			timer.reset();
			timer.pokreni();
		}
	}
	
	public int getNivo() {
		return nivo;
	}
	
	public static void main(String[] args) {
		new Igra();
	}
}
