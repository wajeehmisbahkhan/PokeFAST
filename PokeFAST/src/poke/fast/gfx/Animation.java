package poke.fast.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private int interval, index;		//interval is frequency of frames
	private long lastTime, timer;
	
	private BufferedImage[] frames;
	
	public Animation(int interval, BufferedImage[] frames) {
		this.interval = interval;
		this.frames = frames;
		index = 0; timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if( timer > interval ) {
			index++;
			timer = 0;		//timer reset for next interval
			if(index >= frames.length)
				index = 0;
		}
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
}
