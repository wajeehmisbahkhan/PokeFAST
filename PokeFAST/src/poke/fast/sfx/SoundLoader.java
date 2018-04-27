package poke.fast.sfx;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundLoader {
	
	private static Clip clip;
	
	public static Clip loadSound (String name) {
		try {
			clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/sounds/" + name + ".wav"));
			clip.open(ais);
			return clip;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
