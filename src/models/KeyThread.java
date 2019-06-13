package models;

import views.View;

public class KeyThread implements Runnable {
	private View view;
	private int finger;
	private long delay;
	
	private volatile long endTime;
	private boolean running = true;
	private int keyPressCount = 0;
	
	public KeyThread(View view, int finger, long delay) {
		super();
		this.view = view;
		this.finger = finger;
		this.delay = delay;
		this.keyPressCount = 1;
	}

	@Override
	public void run() {
		this.endTime = System.currentTimeMillis() + this.delay;
		while(this.running) {
			//gdy dojdzie do konca to drukuje znak
			if(this.endTime < System.currentTimeMillis()) {
				
				//drukuj litere
				this.view.print(this.finger, this.keyPressCount);
				
				//po wydrukowaniu zmiana wartosci i konczenie pracy watku
				this.keyPressCount = 0;
				this.running = false;
			}
		}
	}
	
	public synchronized void addTime() {		
		this.endTime = System.currentTimeMillis() + this.delay;
		this.keyPressCount = this.keyPressCount + 1;
	}
	
	public synchronized void end() {
		this.endTime = System.currentTimeMillis();
	}
}