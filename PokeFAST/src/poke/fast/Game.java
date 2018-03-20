package poke.fast;

//The Game Class will consist of the game and all of its necessary components.
public class Game {
	
	//Objects for the constructor
	private String title;
	private int width, height;
	//Display will be created using these properties
	private Display display;
	
	
	
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
}
