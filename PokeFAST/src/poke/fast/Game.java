package poke.fast;

import poke.fast.display.Display;

//The Game Class will consist of the game and all of its necessary components.
public class Game implements Runnable {
	
	//Objects for the constructor
	private String title;
	private int width, height;
	//Display will be created using these properties
	private Display display;
	
	//This thread will be used to execute the game
	private Thread thread;
	//This variable will check whether the game is running or not
	private boolean running;
	
	//The Game constructor will require a title for the game and its height and width
	Game (String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	//Initialize the game
	public void init () {
		//Initialize the Display
		display = new Display(title, width, height);
		
	}

	
	public synchronized void start () { //Synchronized is used to stop conflicts between different threads
		if (running) //If the game is already running
			return;  // We don't want to execute any of this
		running = true; //Since the start method has executed
		thread = new Thread(this); //This will call the game's run method without any breaks
		thread.start(); //The thread has started
	}
	
	//This run method is implementation of the runnable interface
	public void run() {
		//Initialize the game
		init();
		
	}
}
