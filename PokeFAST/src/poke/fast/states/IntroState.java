package poke.fast.states;

import java.awt.Color;
import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.textboxes.DialogueBox;
import poke.fast.transitions.TransitionManager;

public class IntroState extends State {

	private DialogueBox dialogueBox;
	private Color backColor = new Color(20, 20, 20);
	
	private boolean[] states; //Ammi dialogue, fadeout, point
	
	public IntroState(Handler handler) {
		super(handler);
		dialogueBox = new DialogueBox(handler);
		transitionManager = new TransitionManager(handler);
		states = new boolean[3];
		states[0] = true;
		states[1] = false;
		states[2] = false;
	}

	@Override
	public void tick() {
		dialogueBox.tick();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(backColor);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		if (states[0]) {
			dialogueBox.render(g);
			dialogueBox.say(g, "Zzz...Zzz...\nZzzzzz...\nKAMBAKHAT UTH JA!\n\nKia hua ammi!?\nMom: Tumhara point nikal jaiga!\nAcha acha ja raha hoon...");
			if (DialogueBox.said) {
				DialogueBox.said = false;
				states[0] = false;
				states[1] = true;
			}
		}
		else if (states[1]) {
			states[1] = false;
			states[2] = true;
		
		} else if (states[2]) {
			g.drawImage(Assets.bus_back, 0, -30, handler.getWidth(), handler.getHeight(), null);
			dialogueBox.render(g);
			dialogueBox.say(g, "Zindagi main pahli baar itne jaldi uthna para; I hope my first day doesn't go too bad. Suna hai is jagah pai ragging bohat hoti hai... Khair Allah malik hai.");
			if (DialogueBox.said) {
				DialogueBox.said = false;
				states[2] = false;
			}
		} else {
			g.drawImage(Assets.bus_back, 0, -30, handler.getWidth(), handler.getHeight(), null);
			handler.getGame().setGameState(new GameState(handler));
			State.setState(handler.getGame().getGameState());
		}
	}

}
