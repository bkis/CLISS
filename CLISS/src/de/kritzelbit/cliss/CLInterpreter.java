package de.kritzelbit.cliss;

public class CLInterpreter {

	private static final String PATTERN_FILE_NAME = ".*\\..*";
	
	private Sequencer seq;
	
	
	public CLInterpreter(Sequencer seq){
		this.seq = seq;
	}

	public void processCommand(String command) {
		String[] cmd = command.split("\\s");

		if (cmd[0].equalsIgnoreCase("t")) {
			if (cmd.length >= 3){
				seq.setTrack(cmd[1], cmd[2]);
			} else {
				cmdError(command);
			}
		} else if (cmd[0].equalsIgnoreCase("bpm")) {
			if (cmd.length >= 2 && cmd[1].matches("\\d+")){
				seq.setBPM(intVal(cmd[1]));
			} else {
				cmdError(command);
			}
		} else if (cmd[0].equalsIgnoreCase("swing")) {
			if (cmd.length >= 2 && cmd[1].matches("\\d+")){
				seq.setSwing(intVal(cmd[1]));
			} else {
				cmdError(command);
			}
		}
	}
	
	private int intVal(String toParse){
		return Integer.parseInt(toParse);
	}
	
	private void cmdError(String cmd){
		System.err.println("[ERROR]\tinvalid command: \"" + cmd + "\"");
	}

}
