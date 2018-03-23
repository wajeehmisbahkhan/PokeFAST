package poke.fast.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

//This class handles the window display
public class Display {
	
	//JFrame for the window
	private JFrame frame;
	//Canvas for drawing in the frame
	private Canvas canvas;
	
	//Objects for the constructor
	private String title;
	private int width, height;
	
	public Display (String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		createDisplay();
	}
	
	//Create the Display
	private void createDisplay () {
		//Create a frame and pass the title
		frame = new JFrame(title);
		//Set the properties of the JFrame
		//Set size
		frame.setSize(width, height);
		frame.setResizable(false);
		//Set position (center)
		frame.setLocationRelativeTo(null);
		//Display the create frame
		frame.setVisible(true);
		
		//Create a canvas
		canvas = new Canvas();
		//Set the three possible sizes
		canvas.setPreferredSize(new Dimension(width, height)); //Sets preferred size using the dimension object
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		//Make canvas un-focusable so that the JFrame is focused on and keyboard inputs can be taken
		canvas.setFocusable(false);
		//Add this created Canvas to the JFrame
		frame.add(canvas);
		//Resize frame for canvas so it fits perfectly
		frame.pack();
		
		//Close the application properly
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//GETTERS & SETTERS
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
}