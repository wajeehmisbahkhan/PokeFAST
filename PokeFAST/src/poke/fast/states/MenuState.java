package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.gfx.Transition;
import poke.fast.sfx.SoundManager;
import poke.fast.ui.ClickListener;
import poke.fast.ui.UIImageButton;
import poke.fast.ui.UIManager;

//The Menu State or the welcome screen
public class MenuState extends State {

	private UIManager uiManager;
	
	public MenuState (Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		//Add stuff to the menu

		SoundManager.playSound("Kbc", false);
		uiManager.addObject(new UIImageButton(handler.getGame().getWidth()/2 - 80, handler.getGame().getHeight()/2, 160, 45, Assets.btn_start, new ClickListener () {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				Transition.playing = true;
				SoundManager.playSound("clap", false);
			}
		}));
		
	}
	

	public void tick() {
		//If clicked and animation ended
		if (Transition.played) {
			Transition.played = false;
			State.setState(handler.getGame().getGameState());
		}
	}


	public void render(Graphics g) {
		//The background
		g.drawImage(Assets.menuBg, 0, 0, 400, 400, null);
		//The user interface
		uiManager.render(g);
		//Fade out
		if (Transition.playing)
			transition.fadeOut(g, 4);
	}

}
