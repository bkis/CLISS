package de.kritzelbit.cliss;

import java.io.File;
import java.util.Arrays;

public class Track {

	private String id;
	private Sound sound;
	private boolean[] steps;
	
	
	public Track(String id, int steps, File soundFile, boolean[] pattern) {
		this(id, steps, soundFile);
		setPattern(pattern);
	}

	public Track(String id, int steps, File soundFile) {
		this.id = id;
		setSteps(steps, false);
		setSound(soundFile);
	}

	public Sound getSound() {
		return sound;
	}

	public void setSteps(int steps, boolean stretch) {
		if (this.steps == null) {
			this.steps = new boolean[steps];
		} else if (stretch) {
			setSteps(steps, false); // TODO
		} else {
			this.steps = Arrays.copyOf(this.steps, steps);
		}
	}

	public void addStep(int step) {
		setStep(step - 1, true);
	}

	public void removeStep(int step) {
		setStep(step - 1, false);
	}

	private void setStep(int step, boolean active) {
		if (isValidStep(step)) {
			steps[step] = active;
		}
	}

	public void toggleStep(int step) {
		if (isValidStep(step)) {
			steps[step] = !steps[step];
		}
	}

	public boolean getStep(int step) {
		if (isValidStep(step)) {
			return steps[--step];
		} else {
			return false;
		}
	}

	private boolean isValidStep(int step) {
		if (step <= steps.length && step > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void setSound(File soundFile) {
		this.sound = new Sound(soundFile);
	}
	
	public void setPattern(boolean[] pattern){
		if (pattern == null) return;
		for (int i = 0; i < steps.length; i++) {
			if (i < pattern.length){
				steps[i] = pattern[i];
			} else {
				steps[i] = pattern[i % pattern.length];
			}
		}
	}
	
	public void mergePattern(boolean[] pattern){
		for (int i = 0; i < steps.length && i < pattern.length; i++) {
			steps[i] = pattern[i];
		}
	}
	
	@Override
	public String toString() {
		String s = id + "\t[";
		
		for (int i = 0; i < steps.length; i++)
			if (steps[i] == true)
				s += "X";
			else
				s += ".";
		s += "]\tFile: " + sound.getSoundFile().getName();
		
		return s;
	}

}
