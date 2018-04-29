package poke.fast.entities.inanimates;

import java.awt.Rectangle;

import poke.fast.Handler;
import poke.fast.entities.Entity;

public abstract class Inanimate extends Entity {
	
	protected Rectangle interaction;
	protected String message;

	public Inanimate(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
	public boolean isInteracting () {
		if (interaction != null)
			if ((handler.getGame().getGameState().getPlayer().getX() >= this.x + interaction.x && handler.getGame().getGameState().getPlayer().getX() <= this.x + interaction.width + interaction.x)
			&&	(handler.getGame().getGameState().getPlayer().getY() >= this.y + interaction.y && handler.getGame().getGameState().getPlayer().getY() <= this.y + interaction.height + interaction.y))
				return true;
		return false;
	}
	
	public String getMessage() {
		return message;
	}

}
