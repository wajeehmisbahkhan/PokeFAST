package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.gfx.Transition;
import poke.fast.sfx.SoundManager;
import poke.fast.textboxes.DialogueBox;

public class OutroState extends State {

	private DialogueBox dialogueBox;
	private int ticks;
	private int rate;
	private int drate;
	private boolean evolving;
	private Transition transition;
	
	public OutroState(Handler handler) {
		super(handler);
		dialogueBox = new DialogueBox(handler);
		evolving = true;
		ticks = 0;
		rate = 104;
		drate = 3;
	}

	@Override
	public void tick() {
		dialogueBox.tick();
		ticks++;
		//When evolving sound ends
		if (ticks >= 520)
			evolving = false;
	}

	@Override
	public void render(Graphics g) {
		dialogueBox.render(g);
		if (evolving)
			dialogueBox.say(g, "What!? You're evolving!\n\nSo exciting! You is won the game! \n\nMore easter eggs...\nDid you get all of them?");
		else
			dialogueBox.say(g, "Freshman evolved into Sophomore!\n\n\nThanks for playing :)");
		if (ticks % rate == 0) {
			g.drawImage(Assets.sophomore, handler.getWidth()/2 - 100, handler.getHeight()/2 - 100 - 30, 200, 200, null);
			if (rate > 0+drate) {
				rate-=drate;
				drate++;
			} else
				rate = 1;
		}
		else
			g.drawImage(Assets.freshman, handler.getWidth()/2 - 100, handler.getHeight()/2 - 100 - 30, 200, 200, null);
		//When sound ends
		if (ticks >= 830) {
			SoundManager.stopBackground();
			State.setState(new MenuState(handler));
		}
	}
	
}
