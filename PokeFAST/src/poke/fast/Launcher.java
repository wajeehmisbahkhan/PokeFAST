package poke.fast;

public class Launcher {
	public static void main (String[] args) {
		//This main method will instantiate and launch the game
		
		Game game = new Game("PokeFAST", 1366,768);
		game.start();
		
	}
}
