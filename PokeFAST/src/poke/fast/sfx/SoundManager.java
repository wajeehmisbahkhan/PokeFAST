package poke.fast.sfx;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundManager {
	
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
