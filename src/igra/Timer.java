package igra;

import java.awt.Label;

public class Timer extends Thread {
	private int s;
	private Label label;
	private boolean work = false;
	
	public Timer(Label label) {
		this.label = label;
		s = 20;
	}
	
	public synchronized void pokreni() {
		work = true;
		notify();
	}
	
	public synchronized void zaustavi() {
		work = false;
	}
	
	public synchronized void reset() {
		s = 20;
	}
	
	public int getS() {
		return s;
	}

	@Override
	public void run() {
		try {
			
			while(!isInterrupted()) {
				synchronized (this) {
					while(!work) wait();
				}
				
				s--;
				label.setText(toString());
				label.revalidate();
				sleep(1000);
			}
			
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public synchronized String toString() {
		int m = s / 60;
		int ss = s % 60;
		return String.format("%02d:%02d", m, ss);
	}
}
