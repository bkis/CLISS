package de.kritzelbit.cliss;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.UnsupportedAudioFileException;

public class Main {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, InterruptedException {
		Sequencer seq = Sequencer.getInstance();
		seq.addTrack("pew", new File("sound.wav"), new boolean[]{true, false, false, false});
		seq.addTrack("pow", new File("sound2.wav"), new boolean[]{false, true, true, true});
		seq.play();
		
		//INPUT
		Scanner input = new Scanner(System.in);
		String line;
		while (!(line = input.nextLine()).equals("exit")){
			if (line.startsWith("bpm")) seq.setBPM(Integer.parseInt(line.replaceAll("\\D", "")));
		}
		input.close();
		seq.stop();
	}

}
