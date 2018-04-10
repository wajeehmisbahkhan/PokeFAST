package poke.fast;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import poke.fast.display.Display;
import poke.fast.gfx.Assets;
import poke.fast.gfx.GameCamera;
import poke.fast.input.KeyManager;
import poke.fast.input.MouseManager;
import poke.fast.states.GameState;
import poke.fast.states.MenuState;
import poke.fast.states.State;

//The Game Class will consist of the game and all of its necessary components.
public class Game implements Runnable {
	
	//Objects for the constructor
	private String title;
	private int width, height;
	//Display will be created using these properties
	private Display display;
	
	//For drawing things on to the screen
	private BufferStrategy bs;
	private Graphics g;
	
	//This thread will be used to execute the game
	private Thread thread;
	//This variable will check whether the game is running or not
	private boolean running;
	
	//The States
	private State gameState;
	private State menuState;
	private State battleState;
	
	//These are the inputs
	private MouseManager mouseManager;
	private KeyManager keyManager;
	
	//Game Camera
	private GameCamera gameCamera;
	
	//This is the handler for the game
	private Handler handler;
	
	
	
	
	//The Game constructor will require a title for the game and its height and width
	Game (String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		keyManager = new KeyManager();
	}
	
	//Initialize the game
	public void init () {
		//Initialize
		//Display
		display = new Display(title, width, height);
		//Key listener - JFrame
		display.getFrame().addKeyListener(keyManager);
		//Handler
		handler = new Handler(this);
		//Assets
		Assets.init();
		
		//Camera
		gameCamera = new GameCamera(handler);
		
		//Managers
		mouseManager = new MouseManager();
		//keyManager = new KeyManager();
		
		//States
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
		
		//Add the Event Listeners to JFrame
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		//Fire only those which are focused in the canvas, avoids glitching
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
	}
	
	//Keep updating these Managers
	public void tick () {
		keyManager.tick();
		if (State.getState() != null)
			State.getState().tick();
	}

	//Display this every second
	public void render() {
		bs = display.getCanvas().getBufferStrategy(); //Get the current Buffer Strategy
		if (bs == null) { //If it has not been set yet
			display.getCanvas().createBufferStrategy(3); //Create a Triple Buffer Strategy
			return; //Do not execute the drawing code
		}
		//If it has been set, we are ready to draw
		g = bs.getDrawGraphics(); //Our paint brush
		
		//Clear the screen completely
		g.clearRect(0, 0, width, height);
		
		//Let the current state fill the screen
		if (State.getState() != null)
			State.getState().render(g);
		
		//Show what has been drawn
		bs.show();
		//Dispose the brush
		g.dispose();
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
		
		//Timing stuff
		int fps = 60;
		double interval = 1000000000/fps;
		double delta = 0;
		long now, lastTime = System.nanoTime();
		long timer = 0;
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / interval;
			timer += (now - lastTime);
			lastTime = now;		//sed lyf :(
			
			if(delta >= 1) {
				tick();
				render();
				delta=0;
			}
			
			if(timer >= 1000000000) {
				timer = 0;
			}
			
		}
		
	}
	
	
	//GETTERS & SETTERS
	public int getWidth () {
		return width;
	}
	public int getHeight () {
		return height;
	}
	public void setWidth (int w) {
		width = w;
	}
	public void setHeight (int h) {
		height = h;
	}
	public MouseManager getMouseManager () {
		return mouseManager;
	}
	public KeyManager getKeyManager() {
		return keyManager;
	}
	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public State getGameState() {
		return gameState;
	}
	
	public State getMenuState () {
		return menuState;
	}
	
	public State getBattleState () {
		return battleState;
	}
}