package de.kritzelbit.cliss;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sound.sampled.UnsupportedAudioFileException;

public class Sequencer {

	private static Sequencer instance;
	private SoundPlayer player;
	private StepTimer timer;
	private Map<String, Track> tracks;
	private int steps;
	private int step;

	public static Sequencer getInstance() {
		if (instance == null) {
			instance = new Sequencer(16);
		}
		return instance;
	}

	private Sequencer(int beats) {
		this.timer = new StepTimer(this);
		this.player = SoundPlayer.getInstance();
		this.tracks = new HashMap<String, Track>();
		this.steps = beats;
		this.step = 1;
	}
	
	public void addTrack(String trackID, File soundFile) {
		addTrack(trackID, soundFile, null);
	}

	public void addTrack(String trackID, File soundFile, boolean[] pattern) {
		tracks.put(trackID, new Track(trackID, steps, soundFile, pattern));
		System.out.println("ADDED TRACK:\t" + tracks.get(trackID));
	}
	
	public void removeTrack(String trackID){
		tracks.remove(trackID);
	}
	
	public Track getTrack(String trackID){
		return tracks.get(trackID);
	}
	
	public void randomizePattern(String trackID, int densityInPercent){
		boolean[] pattern = new boolean[steps];
		for (int i = 0; i < pattern.length; i++) {
			pattern[i] = Math.random() < (float)densityInPercent/100;
		}
		tracks.get(trackID).setPattern(pattern);
	}

	public void setSteps(int steps) {
		setSteps(steps, false);
	}

	public void setSteps(int step, boolean stretch) {
		this.steps = step;
		for (Entry<String, Track> e : tracks.entrySet()) {
			e.getValue().setSteps(step, stretch);
		}
	}

	public void play() {
		timer.run();
	}

	public void stop() {
		timer.stop();
	}

	public boolean isPlaying() {
		return timer.isRunning();
	}

	public void step() {
		for (Entry<String, Track> e : tracks.entrySet()) {
			if (e.getValue().getStep(step)) {
				try {
					player.play(e.getValue().getSound().getSoundFile());
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		if (step < steps) {
			step++;
		} else {
			step = 1;
		}
	}

	
	public void setBPM(int bpm) {
		timer.setBPM(bpm);
	}

}
