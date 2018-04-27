package poke.fast.states;

import java.awt.Color;
import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.gfx.Transition;
import poke.fast.textboxes.DialogueBox;

public class IntroState extends State {

	private DialogueBox dialogueBox;
	private Transition transition;
	private Color backColor = new Color(20, 20, 20);
	
	public IntroState(Handler handler) {
		super(handler);
		dialogueBox = new DialogueBox(handler);
		transition = new Transition(handler);
	}

	@Override
	public void tick() {
		dialogueBox.tick();
	}

	@Override
	public void render(Graphics g) {
		transition.fadeIn(g, 2);
		g.setColor(backColor);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		dialogueBox.render(g);
		dialogueBox.say(g, "Zzz...Zzz...\n Zzzzzz...\nKAMBAKHAT UTH JA!\n\nKia hua ammi!?\nMom: Tumhara point nikal jaiga!\nAcha acha ja raha hoon...");
		
	}

}
