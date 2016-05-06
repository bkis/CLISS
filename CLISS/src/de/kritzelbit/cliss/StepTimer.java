package de.kritzelbit.cliss;

import java.util.Timer;
import java.util.TimerTask;

public class StepTimer {
	
	private Timer timer;
	private Sequencer sequencer;
	private boolean running;
	private int stepDelay;
	
	
	public StepTimer(Sequencer seq){
		this.sequencer = seq;
		setBPM(120);
	}
	
	
	public void run(){
		this.timer = new Timer();
		step();
		running = true;
	}
	
	
	public void stop(){
		timer.cancel();
		running = false;
	}
	
	
	public boolean isRunning(){
		return running;
	}
	

	private void step(){
		sequencer.step();
		
		TimerTask step = new TimerTask() {
			public void run() {
				step();
			}
		};
		
		timer.schedule(step, stepDelay);
	}
	
	
	public float getBpm() {
		return stepDelay * 60000;
	}


	public void setBPM(float bpm) {
		if (bpm > 1 && bpm < 1000){
			this.stepDelay = (int) (60000 / (bpm*4));
		}
	}

	
}
