package poke.fast.sfx;

import javax.sound.sampled.Clip;

import poke.fast.Handler;
import poke.fast.states.BattleState;
import poke.fast.states.GameState;
import poke.fast.states.OutroState;
import poke.fast.states.State;
import poke.fast.transitions.TransitionManager;

public class SoundManager {
	
	public static Clip background;
	private static String track = "none";
	private Handler handler;
	
	public SoundManager (Handler handler) {
		this.handler = handler;
	}

	//For first time
	public static void setBackground (String name) {
		if (track.equals("none")) {
			background = SoundLoader.loadSound(name);
			background.loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			if (background.getMicrosecondPosition() >= background.getMicrosecondLength()) {
				stopBackground();
				background = SoundLoader.loadSound(name);
				background.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
		track = name;
	}
	
	//Overloading
	public static void setBackground (String name, boolean override) {
		if (override) {
			stopBackground();
			background = SoundLoader.loadSound(name);
			background.loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			if (background.getMicrosecondPosition() >= background.getMicrosecondLength()) {
				stopBackground();
				background = SoundLoader.loadSound(name);
				background.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
		track = name;
	}
	
	public static void stopBackground () {
		background.stop();
		track = "none";
	}
	
	//For playing once
	public static void playSound (String fileName) {
		Clip clip = SoundLoader.loadSound(fileName);
		clip.loop(0);
	}

	//Set the background
	public void tick () {
		if (State.getState() instanceof GameState) {
			if (track.equals("none"))
				setBackground("game");
			else if (!track.equals("game") && !TransitionManager.change)
				setBackground("game", true);
			else if (!track.equals("battle_start") && TransitionManager.change && !handler.getGame().getGameState().victory) {
				setBackground("battle_start", true);
			}
		}
		
		if (State.getState() instanceof BattleState) {
			if (!track.equals("battle_battling") && background.getMicrosecondPosition() >= background.getMicrosecondLength())
				setBackground("battle_battling", true);
		}
		
		
		if (State.getState() instanceof OutroState) {
			if (!track.equals("evolution"))
				setBackground("evolution", true);
		}
	}

}
