package poke.fast.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import poke.fast.Handler;
import poke.fast.ui.UIObject;

//For creating the User Interface
public class UIManager {
	
	//Handler
	private Handler handler;
	
	//An Array List of UIObjects
	private ArrayList<UIObject> objects;
	
	public UIManager (Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}
	
	//Go through the functions of every object
	public void tick () {
		for (UIObject o: objects)
			o.tick();
	}
	
	public void render (Graphics g) {
		for (UIObject o: objects)
			o.render(g);
	}
	
	public void onMouseMove (MouseEvent e) {
		for (UIObject o: objects)
			o.onMouseMove(e);
	}
	
	public void onMouseReleased (MouseEvent e) {
		for (UIObject o: objects)
			o.onMouseRelease(e);
	}
	
	//Add and remove objects
	public void addObject (UIObject o) {
		objects.add(o);
	}
	public void removeObject(UIObject o) {
		objects.remove(o);
	}
	
	//GETTERS & SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
	
}