package poke.fast.sfx;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundManager {
	
	public static Clip background;
	public static String track = "none";
	
	public static void setBackground (String name) {
		if (track.equals("none")) {
			try {
				background = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/sounds/" + name + ".wav"));
				background.open(ais);
				background.loop(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (background.getMicrosecondPosition() >= background.getMicrosecondLength()) {
				try {
					background.stop();
					background = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/sounds/" + name + ".wav"));
					background.open(ais);
					background.loop(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		track = name;
	}
	
	public static void stopBackground () {
		background.stop();
		track = "none";
	}
	
	//NEVER EVER EVER EXECUTE THIS IN A LOOPING METHOD
	public static void playSound (String fileName, boolean loop) {
		try {
			File file = new File("res/sounds/" + fileName + ".wav");
			Clip clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			clip.open(ais);
			if (loop)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			else
				clip.loop(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
