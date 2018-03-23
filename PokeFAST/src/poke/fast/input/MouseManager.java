package poke.fast.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import poke.fast.ui.UIManager;

//This class will define the interface of the mouse events
public class MouseManager implements MouseListener, MouseMotionListener {
	
	//These values will be helpful
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	//The User Interface Manager
	private UIManager uiManager;
	
	public void setUIManager (UIManager uiManager) {
		this.uiManager = uiManager;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if (uiManager != null)
			uiManager.onMouseMove(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if (e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if (e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
		if (uiManager != null)
			uiManager.onMouseReleased(e);
	}
	//NOT NEEDED
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	//GETTERS & SETTERS
	public boolean isLeftPressed () {
		return leftPressed;
	}
	
	public boolean isRightPressed () {
		return rightPressed;
	}
	
	public int getMouseX () {
		return mouseX;
	}
	
	public int getMouseY () {
		return mouseY;
	}
}
