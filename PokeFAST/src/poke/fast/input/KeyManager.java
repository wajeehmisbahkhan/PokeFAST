package poke.fast.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys;
	public boolean up, down, left, right;
	public boolean space;
	
	public boolean spacePressed;
	private int ticks = 0;
	public boolean canMove = true;
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void tick() {
		up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
		space = keys[KeyEvent.VK_SPACE];
		if (space && ticks > 30) {
			spacePressed = true;
			ticks = 0;
		} else
			spacePressed = false;
		ticks++;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (canMove || e.getKeyCode() == KeyEvent.VK_SPACE)
			keys[e.getKeyCode()] = true;
		else
			keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
