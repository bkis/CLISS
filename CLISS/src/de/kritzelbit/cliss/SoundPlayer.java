package de.kritzelbit.cliss;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class SoundPlayer implements LineListener {
	
	private static SoundPlayer instance;
	
	private Clip clip;
	
	
	private SoundPlayer(){
		
	}
	
	public static SoundPlayer getInstance(){
		if (instance == null) instance = new SoundPlayer();
		return instance;
	}

	public void play(AudioInputStream ais) {
		try {
			Line.Info linfo = new Line.Info(Clip.class);
			Line line;
			line = AudioSystem.getLine(linfo);
			clip = (Clip) line;
			clip.addLineListener(this);
			clip.open(ais);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void play(File wav) throws UnsupportedAudioFileException, IOException {
		play(AudioSystem.getAudioInputStream(wav));
	}
	

	public void update(LineEvent le) {
		LineEvent.Type type = le.getType();
		if (type == LineEvent.Type.OPEN) {
			//...
		} else if (type == LineEvent.Type.START) {
			//...
		} else if (type == LineEvent.Type.STOP) {
			//...
		}
	}

	
}