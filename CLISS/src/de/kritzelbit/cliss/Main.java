package de.kritzelbit.cliss;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.UnsupportedAudioFileException;

public class Main {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, InterruptedException {
		Sequencer seq = Sequencer.getInstance();
		CLInterpreter clInterp = new CLInterpreter(seq);
		seq.addTrack("BD", new File("bd.wav"), new boolean[]{true, false, false, false});
		seq.addTrack("HH", new File("hh.wav"), new boolean[]{true, false, true, false, true});
		seq.addTrack("SD", new File("sd.wav"), new boolean[]{false, false, false, false, true, false, false, false});
		seq.play();
		
		//dirty test code
		Scanner input = new Scanner(System.in);
		String line;
		while (!(line = input.nextLine()).equals("exit")){
			clInterp.processCommand(line);
		}
		input.close();
		seq.stop();
	}

}
